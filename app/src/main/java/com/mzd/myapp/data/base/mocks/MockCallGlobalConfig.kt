package com.mzd.myapp.data.base.mocks


/**
 * Class used for the unit test to change the suffix / code / message
 */
class MockCallGlobalConfig private constructor() {
    var suffix = DEFAULT_SUFFIX
    private var code = DEFAULT_CODE
    private var message = DEFAULT_MESSAGE

    // This defines the default behaviour for ALL web services calls. You can then decide, for each WS, if you want this behaviour,
    // or another one. Not used for the moment, so silent the compiler warning
    @Suppress("unused")
    val globalMode = MockServiceConfig.MockMode.LIVE

    @Suppress("unused")
    fun reset() {
        suffix = DEFAULT_SUFFIX
        code = DEFAULT_CODE
        message = DEFAULT_MESSAGE
    }

    companion object {
        const val DEFAULT_SUFFIX = "_success"
        private const val DEFAULT_CODE = 200
        private const val DEFAULT_MESSAGE = "OK"
        @JvmStatic
        var instance: MockCallGlobalConfig? = null
            get() {
                if (field == null) {
                    field =
                        MockCallGlobalConfig()
                }
                return field
            }
            private set
    }
}