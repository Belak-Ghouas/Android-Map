package com.mzd.myapp.data.base

import android.os.Handler
import com.mzd.myapp.data.base.mocks.MockServiceConfig
import retrofit2.Call
import retrofit2.Callback

class CallWrapper<T>(
    private val call: Call<T>,
    private val mockServiceConfig: MockServiceConfig?
) {

    /**
     * Performs the actual call:
     *   - if the mock service config is in LIVE mode, the actual call will be done
     *   - else, it will use the mock'd json file, either in a synchronous manner (if MOCK_SYNC)
     *   or asynchronous (if MOCK_ASYNC)
     */
    fun doCall(callback: Callback<T>) {
        when (mockServiceConfig?.mockMode) {
            MockServiceConfig.MockMode.LIVE -> doLiveCall(callback)
            MockServiceConfig.MockMode.MOCK_ASYNC -> doAsyncMockCall(callback)
            MockServiceConfig.MockMode.MOCK_SYNC -> doSyncMockCall(callback)
        }
    }

    private fun doLiveCall(callBack: Callback<T>) {
        call.enqueue(callBack)
    }

    private fun doSyncMockCall(callBack: Callback<T>) {
        val response = call.execute()
        callBack.onResponse(call, response)
    }

    private fun doAsyncMockCall(callBack: Callback<T>) {
        val h = Handler()
        val r = Runnable {
            val response = call.execute()
            callBack.onResponse(call, response)
        }
        h.postDelayed(r, mockServiceConfig?.mockAsyncDelay ?: 0)
    }

}