package com.example.cryptoapp.domian.usecases

import com.example.cryptoapp.domian.CoinRepository

class GetCoinListUseCase(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.getCoinInfoList()
}