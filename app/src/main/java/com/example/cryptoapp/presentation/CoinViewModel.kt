package com.example.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.repoitory.CoinRepositoryImpl
import com.example.cryptoapp.domian.usecases.GetCoinListUseCase
import com.example.cryptoapp.domian.usecases.GetCoinUseCase
import com.example.cryptoapp.domian.usecases.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinUseCase = GetCoinUseCase(repository)
    private val getCoinListUseCase = GetCoinListUseCase(repository)
    private val loadData = LoadDataUseCase(repository)

    val coinInfoList = getCoinListUseCase()

    fun getDetailInfo(fSym: String) = getCoinUseCase(fSym)

    init {
        viewModelScope.launch {
            loadData()
        }
    }

}