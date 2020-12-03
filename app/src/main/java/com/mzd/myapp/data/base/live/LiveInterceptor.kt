package com.mzd.myapp.data.base.live

import com.mzd.myapp.data.base.mocks.MockServiceConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.Boolean.TRUE

class LiveInterceptor(
    @Suppress("unused")
    private var header: Map<String, String>,
    private var mockServiceConfig: MockServiceConfig?
) : Interceptor {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        // If mocked, nothing will bee done at this stage.
        return if (TRUE == mockServiceConfig?.isMocked) {
            chain.proceed(request)
        } else {
            // Else, proceed with the real request
            request = request.newBuilder()
                .removeHeader("Content-Type")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/json")
                .build()

            chain.proceed(request)
        }
    }
}