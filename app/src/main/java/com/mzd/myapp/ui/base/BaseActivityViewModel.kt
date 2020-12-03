package com.mzd.myapp.ui.base

import android.view.MenuItem
import retrofit2.Call

interface BaseActivityViewModel {
    fun logoutConfirmed()

    fun activityReady()
//
//    fun finishActivity()
//
//    fun navigateToActivity(
//        activityToLaunch: Class<out BaseActivity<out BaseActivityViewModelImpl>>
//    )
//
//    fun navigateToActivity(
//        activityToLaunch: Class<out BaseActivity<out BaseActivityViewModelImpl>>,
//        bundle: Bundle? = null
//    )
//
//    fun navigateToActivity(
//        activityToLaunch: Class<out BaseActivity<out BaseActivityViewModelImpl>>,
//        bundle: Bundle? = null,
//        flags: Int = 0
//    )

    fun showWait(show: Boolean)

    fun showToast(string: String?)

    fun showToast(res: Int)

    fun hideKeyboard()

    fun showSnackBar(snackBarContent: BaseActivityViewModelImpl.SnackBarContent)

    fun onDialogPositive(any: Any?)

    fun onDialogNeutral(any: Any?)

    fun onDialogNegative(any: Any?)

    fun snackBarAction(payload: String?)

    fun onMenuLogout()

    fun onMenuHome()

    fun onMenuSettings()

    fun onMenuAbout()

    fun onAvailabilitySettings()



    interface View {
        fun hideVirtualKeyboard()
        fun showWait(show: Boolean)
        fun showToast(string: String?)
        fun showToast(resId: Int)
        fun onMenuItemSelected(item: MenuItem?)
        fun showServerError(call: Call<*>?, string: String?)
        fun showContacts()
        fun showDocuments()
        fun backAction()
        fun vibrate(duration: Long)
        fun goBackToHome()
        fun goBackToPortfolio()
        fun goBackToOt()
        fun doLogout()
        fun showLogoutConfirmation()
        fun exit()
        fun showVideoHelp(videoPath: String?)
        fun showDocInfo()
    }

}
