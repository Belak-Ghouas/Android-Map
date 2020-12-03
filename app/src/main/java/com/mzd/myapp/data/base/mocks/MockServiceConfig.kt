package com.mzd.myapp.data.base.mocks

import android.content.Context
import com.mzd.myapp.data.base.mocks.MockCallGlobalConfig.Companion.instance

class MockServiceConfig(
    var context: Context,
    var rootFileName: String,
    var mockMode: MockMode = MockMode.LIVE
) {

    var mockAsyncDelay = 2000L
    private val mockCallGlobalConfig = instance

    val defaultRootFileName: String
        get() = rootFileName + mockCallGlobalConfig!!.suffix

    val isMocked: Boolean
        get() = mockMode != MockMode.LIVE

    fun isLive(): Boolean {
        return !isMocked
    }

    enum class MockMode {
        LIVE,       // The default case, to perform the real call
        MOCK_ASYNC, // Mocks the LIVE call, with artificial delay
        MOCK_SYNC   // Used for UT
    }
}