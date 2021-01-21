package com.mzd.myapp.ui.login

import android.content.Context
import com.mzd.myapp.data.models.LoginHistory
import com.mzd.myapp.ui.base.BaseActivityInteraction
import com.mzd.myapp.ui.base.BaseActivityViewModel

interface LoginActivityInteraction : BaseActivityInteraction{
    fun onActivityReady(context: Context)
    fun onLoginChanged(login: String)
    fun onPasswordChanged(password: String)
    fun onLogin(context: Context)
    fun onHistorySelected(loginHistory: LoginHistory)

}