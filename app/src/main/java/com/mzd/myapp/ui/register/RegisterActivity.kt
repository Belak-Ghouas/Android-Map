package com.mzd.myapp.ui.register

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mzd.myapp.R
import com.mzd.myapp.databinding.ActivityRegisterBinding
import com.mzd.myapp.databinding.ActivitySplashBinding
import com.mzd.myapp.ui.base.BaseToolbarActivity
import org.koin.java.KoinJavaComponent


class RegisterActivity: BaseToolbarActivity<RegisterActivityViewModelImpl>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelImpl = KoinJavaComponent.inject(RegisterActivityViewModelImpl::class.java).value

        val binding: ActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.viewModel = viewModelImpl
        binding.lifecycleOwner = this

        initViews()
        initObservers()
        viewModelImpl.activityReady()
    }

    fun initViews(){}
}