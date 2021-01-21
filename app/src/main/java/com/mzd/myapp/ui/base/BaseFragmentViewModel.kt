package com.mzd.myapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseFragmentViewModel : ViewModel(), BaseFragmentInteraction {

    var showWait: MutableLiveData<Boolean> = MutableLiveData(false)
    var toastMessageString: MutableLiveData<String> = MutableLiveData()

    override fun showWait(show: Boolean) {
        showWait.value = show
    }

    override fun showToast(res: String) {
        toastMessageString.value = res
    }

}