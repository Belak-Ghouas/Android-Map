package com.mzd.myapp.ui.splash

import androidx.lifecycle.MutableLiveData
import com.mzd.myapp.ui.Coordinator
import com.mzd.myapp.ui.base.BaseActivityViewModel

class SplashActivityViewModel(coordinator: Coordinator) :
    BaseActivityViewModel(coordinator),
    SplashActivityInteraction {

    val timeout = MutableLiveData(0L)

    override fun activityReady() {
        // The activity is ready.
        super.activityReady()
         timeout.value = 3000
    }

    companion object {
        val TAG = SplashActivityViewModel::class.simpleName
    }

    override fun onSplashFinished() {
        // This will kill the timer, if still running
        timeout.value = 0

        // Then launch the login + kill this one.
        appCoordinator.splashFinished()
    }

}
