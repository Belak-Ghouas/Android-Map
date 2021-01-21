package com.mzd.myapp.ui.home


import com.mzd.myapp.ui.Coordinator
import com.mzd.myapp.ui.base.BaseToolbarActivityInteraction

class HomeActivityViewModel(appCoordinator: Coordinator) :
    BaseToolbarActivityInteraction(appCoordinator),
    HomeActivityViewInteraction {

    override fun onActivityReady() {
        // The activity is ready.
        super.activityReady()

    }



}


