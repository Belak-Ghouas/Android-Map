package com.mzd.myapp.ui.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mzd.myapp.R
import com.mzd.myapp.ui.Coordinator
import com.mzd.myapp.utils.SingleLiveEvent


open class BaseActivityViewModel(val appCoordinator: Coordinator) : ViewModel(),
    BaseActivityInteraction {

    var showWait: SingleLiveEvent<Boolean> = SingleLiveEvent()
    var toastMessageString: MutableLiveData<String> = MutableLiveData()
    var toastMessageRes: MutableLiveData<Int> = MutableLiveData()
    var hideKeyboard: MutableLiveData<Boolean> = MutableLiveData(false)

    companion object {
        var TAG = BaseActivityViewModel::class.simpleName
    }

    override fun logoutConfirmed() {
        TODO("Not yet implemented")
    }

    override fun activityReady() {
        // No default implementation. No UT for this one.
    }

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


    override fun onMenuHome() {
    }

    override fun onMenuSettings() {
        // Navigate to the settings
    }

    override fun onMenuAbout() {
        // navigate to "about"
    }


}