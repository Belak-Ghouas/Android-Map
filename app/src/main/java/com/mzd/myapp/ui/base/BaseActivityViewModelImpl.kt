package com.mzd.myapp.ui.base

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.mzd.myapp.R
import com.mzd.myapp.ui.AppCoordinator
import com.mzd.myapp.utils.SingleLiveEvent

/*fun notifyNetworkStatus(isConnected: Boolean) {
    val v: V = getView()
    if (v != null) {
        Log.d(
            com.psa.containeroptim.ui.base.BaseActivityViewModelImpl.TAG,
            "network state changed"
        )
    }
    fun getView(): V? {
        return if (view != null) view.get() else null
    }
}*/
// public class BaseActivityViewModelImpl<C extends BaseCoordinator> extends ViewModel implements BaseActivityViewModel, SharedPreferences.OnSharedPreferenceChangeListener {
open class BaseActivityViewModelImpl(val appCoordinator: AppCoordinator) : ViewModel(), BaseActivityViewModel {

    // var navigateToActivity: MutableLiveData<NavigateToActivity<BaseActivityViewModelImpl>> = MutableLiveData()
    // var finishActivity: MutableLiveData<Boolean> = MutableLiveData(false)
    var showWait: SingleLiveEvent<Boolean> = SingleLiveEvent()
    var toastMessageString: MutableLiveData<String> = MutableLiveData()
    var toastMessageRes: MutableLiveData<Int> = MutableLiveData()
    var hideKeyboard: MutableLiveData<Boolean> = MutableLiveData(false)
    val snackbarContent: MutableLiveData<SnackBarContent> = MutableLiveData()
    val dialog = MutableLiveData<Dialog>()

    companion object {
        var TAG = BaseActivityViewModelImpl::class.simpleName
    }

    override fun logoutConfirmed() {
        TODO("Not yet implemented")
    }

    override fun activityReady() {
        // No default implementation. No UT for this one.
    }

//    override fun finishActivity() {
//        finishActivity.value = true
//    }

//    override fun navigateToActivity(activityToLaunch: Class<out BaseActivity<out BaseActivityViewModelImpl>>) {
//
//        navigateToActivity.value = NavigateToActivity(activityToLaunch)
//    }
//
//    override fun navigateToActivity(
//        activityToLaunch: Class<out BaseActivity<out BaseActivityViewModelImpl>>,
//        bundle: Bundle?
//    ) {
//
//        navigateToActivity.value = NavigateToActivity(activityToLaunch, bundle)
//    }
//
//    override fun navigateToActivity(
//        activityToLaunch: Class<out BaseActivity<out BaseActivityViewModelImpl>>,
//        bundle: Bundle?,
//        flags: Int
//    ) {
//
//        navigateToActivity.value = NavigateToActivity(activityToLaunch, bundle, flags)
//    }

    override fun showWait(show: Boolean) {
        showWait.value = show
    }

    override fun showToast(string: String?) {
        if (string != null) {
            toastMessageString.value = string
        } else {
            Log.w(TAG, "Null text for toast, toast is skipped")
        }
    }

    override fun showToast(res: Int) {
        toastMessageRes.value = res
    }

    override fun hideKeyboard() {
        hideKeyboard.value = true
    }

    override fun showSnackBar(snackBarContent: SnackBarContent) {
        this.snackbarContent.value = snackBarContent
    }

    override fun onDialogPositive(any: Any?) {
        // Implemented in subclass. No UT for now
    }

    override fun onDialogNeutral(any: Any?) {
        // Implemented in subclass. No UT for now
    }

    override fun onDialogNegative(any: Any?) {
        // Implemented in subclass. No UT for now
    }

    override fun snackBarAction(payload: String?) {
        // Implemented in subclass. No UT for now
    }

    override fun onMenuLogout() {
        // Ask for confirmation.
        dialog.value = Dialog.Builder()
            .titleRes(R.string.dialog_logout_title)
            .messageRes(R.string.dialog_logout_message)
            .showButtons(Dialog.POSITIVE or Dialog.NEGATIVE)
            .build()
    }

    override fun onMenuHome() {
//        // If we are on the home activity, we ask for confirmation, else, just exit.
//        if (this is HomeActivityViewModelImpl) {
//            // Now, asking for exit confirmation
//            dialog.value = Dialog.Builder()
//                .payload(this)
//                .titleRes(R.string.home_exit_title)
//                .messageRes(R.string.home_exit_message)
//                .showButtons(Dialog.POSITIVE or Dialog.NEGATIVE)
//                .build()
//        } else {
//            finishActivity()
//        }

//        finishActivity()
    }

    override fun onMenuSettings() {
        // Navigate to the settings
    }

    override fun onMenuAbout() {
        // navigate to "about"
    }

    override fun onAvailabilitySettings() {
        // navigateToActivity(AvailabilityActivity::class.java, null, Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }


    class NavigateToActivity<VM : BaseActivityViewModelImpl>(
        val activityToLaunch: Class<out BaseActivity<out VM>>,
        val bundle: Bundle? = null,
        val flags: Int = 0
    )

    // 'open' for UT purposes.
    open class SnackBarContent(
        val messageResId: Int,
        val duration: Int = Snackbar.LENGTH_LONG,
        val actionResId: Int = 0,
        val payload: String? = null
    )

}