package com.mzd.myapp.di

import com.mzd.myapp.data.base.live.NetworkConfig
import com.mzd.myapp.data.storage.sharedpreferences.sharedpreferences.AppSharedPreferences
import com.mzd.myapp.ui.AppCoordinator
import com.mzd.myapp.ui.home.HomeActivityViewModelImpl
import com.mzd.myapp.ui.login.LoginActivityViewModelImpl
import com.mzd.myapp.ui.splash.SplashActivityViewModelImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val diModules = module {

    // once
    single { AppCoordinator() }
    single { NetworkConfig() }
    single { AppSharedPreferences(androidApplication()) }

    // single instance, used thought out the whole app. This view-model requires its own repository
    viewModel { SplashActivityViewModelImpl(get()) }
    viewModel { LoginActivityViewModelImpl(get(), get()) }
    viewModel { HomeActivityViewModelImpl(get()) }

    // Service calls

    factory {

    }

}