package com.example.cryptoapp.presentation

import android.app.Application

class CoinApplication: Application() {

    val component by lazy {

    }

//    val component by lazy {
//        DaggerApplicationComponent.factory()
//            .create(this, System.currentTimeMillis())
//    }
}