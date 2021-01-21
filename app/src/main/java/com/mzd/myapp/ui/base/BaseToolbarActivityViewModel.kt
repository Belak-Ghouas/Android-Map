package com.mzd.myapp.ui.base

import android.view.MenuItem

//interface BaseToolbarActivityViewModel

interface BaseToolbarActivityViewModel :
    BaseActivityInteraction{
    //fun logoutConfirmed()

    fun viewDestroyed()
    fun onOptionsItemSelected(item: MenuItem?): Boolean


}
