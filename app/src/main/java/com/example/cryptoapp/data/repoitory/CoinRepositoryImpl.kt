package com.example.cryptoapp.data.repoitory

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.domian.CoinInfo
import com.example.cryptoapp.domian.CoinRepository

class CoinRepositoryImpl(
    application: Application
) : CoinRepository {

    private val coinInfoDAO = AppDatabase.getInstance(application).coinPriceInfoDao()

    private val mapper = CoinMapper()

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
}