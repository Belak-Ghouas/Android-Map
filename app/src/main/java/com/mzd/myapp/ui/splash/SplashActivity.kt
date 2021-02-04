package com.mzd.myapp.ui.splash

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.mzd.myapp.R
import com.mzd.myapp.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SplashActivity : BaseActivity<SplashActivityViewModel>(), Runnable {
    private val h = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =getViewModel()
        setContentView(R.layout.activity_splash)
        initViews()
        initObservers()
        viewModel.activityReady()
    }


    override fun initObservers() {
        super.initObservers()

          viewModel.timeout.observe(this, Observer<Long> {
            handleTimeout(it)
        })
    }


    override fun initViews() {
      /*  cl_splash_container.setOnClickListener {
         viewModel.onSplashFinished()
        }
        tv_app_version.text = versionString
        tv_app_name.text=getString(R.string.app_name)*/
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
        viewModel.onSplashFinished()
    }

}

