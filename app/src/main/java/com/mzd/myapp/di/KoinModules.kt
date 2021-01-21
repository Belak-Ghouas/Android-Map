package com.mzd.myapp.di

import com.mzd.myapp.data.base.live.NetworkConfig
import com.mzd.myapp.data.storage.sharedpreferences.sharedpreferences.AppSharedPreferences
import com.mzd.myapp.ui.Coordinator
import com.mzd.myapp.ui.home.HomeActivityViewModel
import com.mzd.myapp.ui.login.LoginActivityViewModel
import com.mzd.myapp.ui.splash.SplashActivityViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val diModules = module {

    // once
    single { Coordinator() }
    single { NetworkConfig() }
    single { AppSharedPreferences(androidApplication()) }

    // single instance, used thought out the whole app. This view-model requires its own repository
    viewModel { SplashActivityViewModel(get()) }
    viewModel { LoginActivityViewModel(get(), get()) }
    viewModel { HomeActivityViewModel(get()) }

    // Service calls

    factory {

    }

}