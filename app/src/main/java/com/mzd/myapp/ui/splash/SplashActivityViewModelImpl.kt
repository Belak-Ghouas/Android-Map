package com.mzd.myapp.ui.splash

import androidx.lifecycle.MutableLiveData
import com.mzd.myapp.ui.AppCoordinator
import com.mzd.myapp.ui.base.BaseActivityViewModelImpl

class SplashActivityViewModelImpl(coordinator: AppCoordinator) :
    BaseActivityViewModelImpl(coordinator),
    SplashActivityViewModel {

    val timeout = MutableLiveData(0L)

    override fun activityReady() {
        // The activity is ready.
        super.activityReady()
         timeout.value = 3000
    }

    companion object {
        val TAG = SplashActivityViewModelImpl::class.simpleName
    }

    override fun onSplashFinished() {
        // This will kill the timer, if still running
        timeout.value = 0

        // Then launch the login + kill this one.
        appCoordinator.splashFinished()
    }

}
