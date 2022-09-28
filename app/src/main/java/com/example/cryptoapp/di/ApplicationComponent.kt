package com.example.cryptoapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class])

interface ApplicationComponent {

//    fun inject(viewModel: CoinViewModel)
//    fun activityComponentFactory(): ActivityComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}