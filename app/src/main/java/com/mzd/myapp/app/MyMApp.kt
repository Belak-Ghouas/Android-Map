package com.mzd.myapp.app

import android.app.Application
import com.mzd.myapp.di.diModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


open class MyMApp : Application() {


    override fun onCreate() {
        super.onCreate()

        if (!isInTest()) {
            // Start Koin
            startKoin {
                androidLogger()
                androidContext(this@MyMApp)
                modules(diModules)
            }
        }
    }

    open fun isInTest(): Boolean {
        return false
    }



}