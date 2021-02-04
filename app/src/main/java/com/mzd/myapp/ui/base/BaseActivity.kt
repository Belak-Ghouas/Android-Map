package com.mzd.myapp.ui.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mzd.myapp.R
import com.mzd.myapp.data.base.ActivityParams
import kotlinx.android.synthetic.main.custom_black_toolbar.*


abstract class BaseActivity<VM : BaseActivityViewModel> : AppCompatActivity() {
    private var params: String? = null
    private var uid = 0

    lateinit var viewModel: VM

    companion object {
        var TAG = BaseActivity::class.simpleName
        val UID_KEY = "key:activity_uid"
        val DATA_KEY = "key:data"
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val i = intent

        if (i != null) {
            uid = i.getIntExtra(UID_KEY, 0)
            params = i.getStringExtra(DATA_KEY)
        }
        supportActionBar?.hide()
    }

    open fun initObservers() {

        this.viewModel.toastMessageString.observe(this, showToastMessageString)
        this.viewModel.toastMessageRes.observe(this, showToastMessageRes)
        this.viewModel.appCoordinator.activityParams.observe(this, Observer<ActivityParams> {
            handleActivityParams(it)
        })
        this.viewModel.appCoordinator.finishCurrentActivity.observe(this, Observer {
            handleFinishActivity(it)
        })
    }


    private fun handleActivityParams(activityParams: ActivityParams) {
        val i = Intent(this, activityParams.activity)
        val params: String? = activityParams.params

        val bundle = Bundle()

        params?.let {
            bundle.putString(DATA_KEY, it)
        }

        i.putExtras(bundle)


        startActivity(i)
    }

    private fun handleFinishActivity(finish: Boolean) {
        if (finish) {
            finish()
        }
    }


    // Listener triggered when activity has to be killed.
    private val activityToFinish = Observer<Boolean> {
        if (it) {
            finish()
        }
    }

    // Listener triggered to show a toast, based on an explicit string
    private val showToastMessageString = Observer<String> {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    // Listener triggered to show a toast, based on a resource id
    private val showToastMessageRes = Observer<Int> {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    open fun initViews(){

    }






}