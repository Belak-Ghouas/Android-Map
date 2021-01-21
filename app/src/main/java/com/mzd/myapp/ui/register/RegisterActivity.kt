package com.mzd.myapp.ui.register

import android.os.Bundle
import com.mzd.myapp.R
import com.mzd.myapp.ui.base.BaseToolbarActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel


class RegisterActivity: BaseToolbarActivity<RegisterActivityViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =getViewModel()
        setContentView(R.layout.activity_register)
        initViews()
        initObservers()
        viewModel.activityReady()
    }

   override fun initViews(){}
}