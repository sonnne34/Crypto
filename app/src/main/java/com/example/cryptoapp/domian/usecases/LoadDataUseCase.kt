package com.example.cryptoapp.domian.usecases

import com.example.cryptoapp.domian.CoinRepository

class LoadDataUseCase(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() = repository.loadData()
}