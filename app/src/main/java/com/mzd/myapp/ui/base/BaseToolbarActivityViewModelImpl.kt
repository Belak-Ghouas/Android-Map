package com.mzd.myapp.ui.base

import android.view.MenuItem
import com.mzd.myapp.ui.AppCoordinator

open class BaseToolbarActivityViewModelImpl(appCoordinator: AppCoordinator) : BaseActivityViewModelImpl(appCoordinator),
    BaseToolbarActivityViewModel {
    override fun viewDestroyed() {
        TODO("Not yet implemented")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        TODO("Not yet implemented")
    }
}

