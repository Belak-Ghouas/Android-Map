package com.mzd.myapp.ui.base

interface BaseActivityInteraction {

    fun logoutConfirmed()
    fun activityReady()


    fun onMenuHome()

    fun onMenuSettings()

    fun onMenuAbout()
    fun showWait(show: Boolean)

    fun showToast(string: String?)

    fun showToast(res: Int)

}