package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.database.CoinInfoDBModel
import com.example.cryptoapp.data.network.models.*
import com.google.gson.Gson

class CoinMapper {

    fun mapDtoToDBModel(coinDto: CoinInfoDto) = CoinInfoDBModel(
        toSymbol = coinDto.toSymbol,
        fromSymbol = coinDto.fromSymbol,
        price = coinDto.price,
        lastUpdate = coinDto.lastUpdate,
        highDay = coinDto.highDay,
        lowDay = coinDto.lowDay,
        lastMarket = coinDto.lastMarket,
        imageUrl = coinDto.imageUrl
    )

    fun mapJsonContainerToListCoinInfo(coinName: CoinInfoJSONContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = coinName.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapCoinNamesToCoinNamesList(coinName: CoinNamesListDto): String {
        return coinName.nameDto?.map { it.coinNameDto?.name }?.joinToString(",") ?: ""
    }
}