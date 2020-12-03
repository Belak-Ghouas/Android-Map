package com.mzd.myapp.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mzd.myapp.R
import com.mzd.myapp.data.base.ActivityParams


abstract class BaseActivity<VM : BaseActivityViewModelImpl> : AppCompatActivity(),
    BaseFragment.OnFragmentListener {
    protected var params: String? = null
    private var uid = 0

    lateinit var viewModelImpl: VM
    protected var viewModel: VM? = null

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

        viewModelImpl.showWait.observe(this, showWaitObserver)
        viewModelImpl.toastMessageString.observe(this, showToastMessageString)
        viewModelImpl.toastMessageRes.observe(this, showToastMessageRes)
        viewModelImpl.hideKeyboard.observe(this, hideKeyboardObserver)
        viewModelImpl.dialog.observe(this, showDialogObserver)
        viewModelImpl.snackbarContent.observe(this, showSnackBarObserver)

        viewModelImpl.appCoordinator.activityParams.observe(this, Observer<ActivityParams> {
            handleActivityParams(it)
        })
        viewModelImpl.appCoordinator.finishCurrentActivity.observe(this, Observer {
            handleFinishActivity(it)
        })
    }


    private fun handleActivityParams(activityParams: ActivityParams) {
        val i = Intent(this, activityParams.activity)
        val params: String? = activityParams.getParams()
        val flags: Int? = activityParams.flags
        val bundle = Bundle()

        // In any cases, we forward a uid. This will be used to sync the stack of active activities
        bundle.putInt(UID_KEY, activityParams.uid)

        // Do we have custom data for this activity?
        params?.let {
            bundle.putString(DATA_KEY, it)
        }

        i.putExtras(bundle)

        flags?.let {
            if (it != 0) {
                i.addFlags(it)
            }
        }

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

    // Listener triggered when activity has to be launched.
    private val activityToLaunch =
        Observer<BaseActivityViewModelImpl.NavigateToActivity<out BaseActivityViewModelImpl>> {
            val i = Intent(this, it.activityToLaunch)

            if (it.bundle != null) {
                i.putExtras(it.bundle)
            }

            i.addFlags(it.flags)

            startActivity(i)
        }

    // Listener triggered when show "wait" animation
    private val showWaitObserver = Observer<Boolean> {
        val waitLayout = findViewById<ViewGroup>(R.id.cl_wait_container)
        waitLayout?.visibility = if (it) View.VISIBLE else View.GONE
    }

    // Listener triggered to show a toast, based on an explicit string
    private val showToastMessageString = Observer<String> {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    // Listener triggered to show a toast, based on a resource id
    private val showToastMessageRes = Observer<Int> {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    // Listener triggered to hide the virtual keyboard
    private val hideKeyboardObserver = Observer<Boolean> {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        var view = currentFocus

        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }

        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Listener triggered to show a dialog.
    private val showDialogObserver = Observer<Dialog> {
        val alertDialogBuilder = AlertDialog.Builder(this)

        if (it.titleRes > 0) {
            alertDialogBuilder.setTitle(getString(it.titleRes))
        }

        if (it.messageRes > 0) {
            alertDialogBuilder.setMessage(it.messageRes)
        }

        if (it.showButtons and Dialog.POSITIVE != 0) {
            alertDialogBuilder.setPositiveButton(getString(it.positiveButtonRes)) { dialog, _ ->
                dialog.dismiss()
                viewModelImpl.onDialogPositive(it.payload)
            }
        }

        if (it.showButtons and Dialog.NEUTRAL != 0) {
            alertDialogBuilder.setNeutralButton(getString(it.neutralButtonRes)) { dialog, _ ->
                dialog.dismiss()
                viewModelImpl.onDialogNeutral(it.payload)
            }
        }

        if (it.showButtons and Dialog.NEGATIVE != 0) {
            alertDialogBuilder.setNegativeButton(getString(it.negativeButtonRes)) { dialog, _ ->
                dialog.dismiss()
                viewModelImpl.onDialogNegative(it.payload)
            }
        }

        alertDialogBuilder
            .setCancelable(it.cancelable)
            .create()
            .show()
    }

    // Listener triggered to show a snackbar
    private val showSnackBarObserver =
        Observer<BaseActivityViewModelImpl.SnackBarContent> { snackBarContent ->
//            val view: CoordinatorLayout? = findViewById(R.id.cl_coordinator_layout)
//
//            if (view != null) {
//                val snackbar = Snackbar.make(
//                    view,
//                    snackBarContent.messageResId,
//                    snackBarContent.duration
//                )
//
//                if (snackBarContent.actionResId != 0) {
//                    snackbar.setAction(snackBarContent.actionResId) { snackBarAction(snackBarContent.payload) }
//                }
//
//                snackbar.show()
//
//            } else {
//                e(
//                    TAG,
//                    "Missing coordinator layout for snackbar, should contain id=R.id.cl_coordinator_layout"
//                )
//            }
        }

    private fun snackBarAction(payload: String?) {
        viewModelImpl.snackBarAction(payload)
    }

    override fun showToastFromFragment(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    override fun showWaitFromFragment(show: Boolean) {
        val waitLayout = findViewById<ViewGroup>(R.id.cl_wait_container)
        waitLayout?.visibility = if (show) View.VISIBLE else View.GONE
    }


}