package com.example.cryptoapp.domian.usecases

import com.example.cryptoapp.domian.CoinRepository

class GetCoinUseCase(
    private val repository: CoinRepository
) {
    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}