package com.mzd.myapp.ui.base

import android.view.MenuItem
import com.mzd.myapp.ui.Coordinator

open class BaseToolbarActivityInteraction(appCoordinator: Coordinator) : BaseActivityViewModel(appCoordinator),
    BaseToolbarActivityViewModel {
    override fun viewDestroyed() {
        TODO("Not yet implemented")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        TODO("Not yet implemented")
    }
}

