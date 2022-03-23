package com.example.cryptoapp.data.network.models

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoJSONContainerDto(
    @SerializedName("RAW")
    @Expose
    val json: JsonObject? = null
)
