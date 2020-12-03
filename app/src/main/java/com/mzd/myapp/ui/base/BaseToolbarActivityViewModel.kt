package com.mzd.myapp.ui.base

import android.view.MenuItem

//interface BaseToolbarActivityViewModel

interface BaseToolbarActivityViewModel : BaseActivityViewModel {
    //fun logoutConfirmed()

    fun viewDestroyed()
    fun onOptionsItemSelected(item: MenuItem?): Boolean


    interface View : BaseActivityViewModel.View {
        fun setToolbarTitle(title: String?)
        fun setToolbarTitle(titleRes: Int)
        fun setToolbarSubtitle(subtitle: String?)
        fun showMenuItem(item: MenuItem?, show: Boolean)
    }




}
