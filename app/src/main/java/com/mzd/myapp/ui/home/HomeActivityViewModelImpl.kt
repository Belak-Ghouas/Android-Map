package com.mzd.myapp.ui.home


import android.content.Context
import com.mzd.myapp.ui.AppCoordinator

import com.mzd.myapp.ui.base.BaseToolbarActivityViewModelImpl

class HomeActivityViewModelImpl(appCoordinator: AppCoordinator) :
    BaseToolbarActivityViewModelImpl(appCoordinator),
    HomeActivityViewModel {

    override fun onActivityReady(context: Context) {
        // The activity is ready.
        super.activityReady()

    }

    override fun onOpenUnitySceneClicked() {
        // call coordinator to launch the appropriate activity

      //  appCoordinator.gotoUnityScene("test")

    }

    override fun onCheckPalet(context: Context) {

        appCoordinator.gotoCheckPallet()
    }

    override fun onCheckContainer(context: Context) {

        appCoordinator.gotoCheckContainer()

    }

}


