package com.mzd.myapp.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

open class BaseFragment : Fragment() {

    protected lateinit var viewModelImpl: BaseFragmentViewModelImpl
    private var activityListener: OnFragmentListener? = null

    @Suppress("unused")
    fun provideViewModel(baseViewModelImpl: BaseFragmentViewModelImpl) {
        this.viewModelImpl = baseViewModelImpl

        viewModelImpl.showWait.observe(viewLifecycleOwner, showWaitObserver)
        viewModelImpl.toastMessageString.observe(viewLifecycleOwner, showToastMessageString)
    }

    override fun onActivityCreated(bundle: Bundle?) {
        super.onActivityCreated(bundle)

      //  activityListener = activity as OnFragmentListener
    }

    private val showWaitObserver = Observer<Boolean> {

        activityListener?.showWaitFromFragment(it)
    }


    private val showToastMessageString = Observer<String> {
        activityListener?.showToastFromFragment(it)
    }

    internal interface OnFragmentListener {
        fun showToastFromFragment(string: String)

        fun showWaitFromFragment(show: Boolean)
    }
}