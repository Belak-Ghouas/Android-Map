package com.mzd.myapp.data.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import java.util.*

abstract class BaseCoordinator {

    companion object{
         val TAG = BaseCoordinator::class.java.simpleName
    }
    val activityParams = SingleLiveEvent<ActivityParams>()
    val finishCurrentActivity = SingleLiveEvent<Boolean>()


    private var registry = HashMap<String,ActivityParams?>()


    protected open fun popCurrentAndPushNewActivity(to: String) {
        popCurrentAndPushNewActivity(to, null)
    }

    protected open fun popCurrentAndPushNewActivity(to: String, params: String?) {
        pushNewActivity(to, params)
        finishCurrentActivity()
    }


    protected open fun pushNewActivity(to: String) {
        pushNewActivity(to, null)
    }

    protected open fun pushNewActivity(to: String, params: String?) {
        val activityParams = registry[to]
        if (activityParams != null) {
            activityParams.params = params
            this.activityParams.postValue(activityParams)
        } else {
            Log.w(TAG, "No activity registered for path '$to'")
        }
    }


    open fun finishCurrentActivity() {
        finishCurrentActivity.postValue(java.lang.Boolean.TRUE)
    }

    /**
     * Registers a route for this coordinator.
     *
     * @param key    the route to launch
     * @param params the mapped activity and launch parameters
     */
    protected open fun register(key: String?, params: ActivityParams?) {
      registry[key!!]=params
    }

    /**
     * by default, finishes the current activity and updates the stack. Override in dedicated
     * coordinator if this behaviour is not the expected one.
     */
    open fun onBackPressed() {

        // And reflect in the view
        finishCurrentActivity()
    }

}