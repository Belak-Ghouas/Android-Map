package com.mzd.myapp.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

open class BaseFragment : Fragment() {

    private lateinit var viewModel: BaseFragmentViewModel
    private var activityListener: OnFragmentListener? = null

    @Suppress("unused")
    fun provideViewModel(baseViewModelImpl: BaseFragmentViewModel) {
        this.viewModel = baseViewModelImpl

        viewModel.showWait.observe(viewLifecycleOwner, showWaitObserver)
        viewModel.toastMessageString.observe(viewLifecycleOwner, showToastMessageString)
    }

    override fun onActivityCreated(bundle: Bundle?) {
        super.onActivityCreated(bundle)


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