package com.mzd.myapp.ui.splash

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mzd.myapp.R
import com.mzd.myapp.databinding.ActivitySplashBinding
import com.mzd.myapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.java.KoinJavaComponent

class SplashActivity : BaseActivity<SplashActivityViewModelImpl>(), Runnable {
    private val h = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelImpl =getViewModel()

        val binding: ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.viewModel = viewModelImpl
        binding.lifecycleOwner = this

        initViews()
        initObservers()
        viewModelImpl.activityReady()
    }


    override fun initObservers() {
        super.initObservers()

          viewModelImpl.timeout.observe(this, Observer<Long> {
            handleTimeout(it)
        })
    }


    private fun initViews() {
        tv_app_version.text = versionString
        tv_app_name.text=getString(R.string.app_name)
    }

    private fun handleTimeout(duration: Long) {
        if (duration > 0) {
            h.postDelayed(this, duration)
        } else {
            h.removeCallbacks(this)
        }
    }

    private val versionString: String
        get() = getString(R.string.app_version, getString(R.string.app_name), versionName)

    private val versionName: String
        get() {
            var version = "N/A"
            try {
                val pInfo = packageManager.getPackageInfo(packageName, 0)
                version = pInfo.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return version
        }

    override fun run() {
        viewModelImpl.onSplashFinished()
    }

}

