package com.mzd.myapp.ui.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.mzd.myapp.data.models.LoginHistory
import com.mzd.myapp.data.storage.sharedpreferences.sharedpreferences.AppSharedPreferences
import com.mzd.myapp.ui.AppCoordinator
import com.mzd.myapp.ui.base.BaseActivityViewModelImpl

class LoginActivityViewModelImpl(coordinator: AppCoordinator, val sharedPrefs: AppSharedPreferences) :
    BaseActivityViewModelImpl(coordinator),
    LoginActivityViewModel {

    private var _login: String? = null
    private var _password: String? = null

    // By default, we can't login.
    var loginFormValid = MutableLiveData(false)
    var loginHistories = MutableLiveData<List<LoginHistory>>()
    var login = MutableLiveData<String?>()
    var password = MutableLiveData<String?>()

    override fun onActivityReady(context: Context) {
        // The activity is ready.
        super.activityReady()

        loginHistories.value = AppSharedPreferences.getLoginHistory(context)
        val lastUsed = loginHistories.value?.firstOrNull()
        if (lastUsed != null) {
            login.value = lastUsed.loginString
        } else {
            login.value = null
        }
        _login = login.value
        // In all case, the password must be empty. Set it, just to be sure
        _password = null
    }

    override fun onLoginChanged(login: String) {
        this._login = login
        checkFormValidity()
    }

    override fun onPasswordChanged(password: String) {
        this._password = password
        checkFormValidity()
    }

    private fun checkFormValidity() {
        // TODO: report the rules
        loginFormValid.value = _login?.isNotEmpty() == true && _password?.isNotEmpty() == true
    }

    override fun onLogin(context: Context) {
        showWait(true)
        // For the sake of this demo, we assume the login is valid, and we add the entry in the history of entries.
        // The code below should be done ONLY upon successful login.
        _login?.let {
            AppSharedPreferences.addOrUpdateLoginHistory(context, LoginHistory(it))
        }
        appCoordinator.gotoHome()
    }

    override fun onHistorySelected(loginHistory: LoginHistory) {
        _login = loginHistory.loginString
        login.value = loginHistory.loginString
        password.value = null
        checkFormValidity()
    }


    companion object {
        val TAG = LoginActivityViewModelImpl::class.simpleName
    }


}
