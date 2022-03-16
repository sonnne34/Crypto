package com.example.cryptoapp.data.repoitory

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.domian.CoinInfo
import com.example.cryptoapp.domian.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    application: Application
) : CoinRepository {

    private val coinInfoDAO = AppDatabase.getInstance(application).coinPriceInfoDao()

    private val mapper = CoinMapper()

    private val apiService = ApiFactory.apiService

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(
            coinInfoDAO.getPriceList()
        ) { it ->
            it.map {
                mapper.mapDBModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(
            coinInfoDAO.getPriceInfoAboutCoin(fromSymbol)
        ) {
            mapper.mapDBModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val top = apiService.getTopCoinsInfo(limit = 50)
                val fSymbols = mapper.mapCoinNamesToString(top)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSymbols)
                val coinInfoDBList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDBList.map { mapper.mapDtoToDBModel(it) }
                coinInfoDAO.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}