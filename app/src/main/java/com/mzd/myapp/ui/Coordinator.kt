package com.mzd.myapp.ui

import com.mzd.myapp.data.base.ActivityParams
import com.mzd.myapp.data.base.BaseCoordinator
import com.mzd.myapp.ui.home.HomeActivity
import com.mzd.myapp.ui.login.LoginActivity
import com.mzd.myapp.ui.register.RegisterActivity

class Coordinator: BaseCoordinator() {
  companion object {
      private val TAG = Coordinator::class.java.simpleName
      private const val LOGIN_ACTIVITY_KEY = "loginActivity"
      private const val HOME_ACTIVITY_KEY = "homeActivity"
      private const val REGISTER_ACTIVITY_KEY = "RegisterActivity"
  }

    init {
        register(Coordinator.LOGIN_ACTIVITY_KEY, ActivityParams(LoginActivity::class.java))
        register(Coordinator.HOME_ACTIVITY_KEY, ActivityParams(HomeActivity::class.java))
        register(Coordinator.REGISTER_ACTIVITY_KEY, ActivityParams(RegisterActivity::class.java))
    }

    fun splashFinished() {
        popCurrentAndPushNewActivity(Coordinator.LOGIN_ACTIVITY_KEY)
    }


    /* public void logOut() {
        popCurrentAndPushNewActivity(LOGIN_ACTIVITY_KEY);

    }*/
    fun gotoRegister() {
        pushNewActivity(Coordinator.REGISTER_ACTIVITY_KEY)
    }

    fun gotoHome() {
        pushNewActivity(Coordinator.HOME_ACTIVITY_KEY)
    }

}