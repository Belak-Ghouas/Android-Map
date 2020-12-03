package com.mzd.myapp.data.base.live

import com.mzd.myapp.data.base.mocks.MockInterceptor
import com.mzd.myapp.data.base.mocks.MockServiceConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseServiceCall protected constructor(
    @Suppress("unused")
    private val networkConfig: NetworkConfig,
    var mockServiceConfig: MockServiceConfig?
) {
    private lateinit var headers: HashMap<String, String>

    protected val requestBuilder: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(NetworkConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildHttpClient())
            .build()

    private fun buildHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()

        httpClientBuilder.addInterceptor(
            LiveInterceptor(
                headers,
                mockServiceConfig
            )
        )

        // Now, add the fake interceptor
        if (mockServiceConfig != null) {
            httpClientBuilder.addInterceptor(
                MockInterceptor(
                    mockServiceConfig!!.context,
                    mockServiceConfig
                )
            )
        }

        // Add Logging interceptor to log network requests
        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(loggerInterceptor)
        return httpClientBuilder.build()
    }

    private fun initHeadersRequest() {
        headers = HashMap()
    }

    companion object {
        @Suppress("unused")
        private val TAG = BaseServiceCall::class.java.name
    }

    init {
        initHeadersRequest()
    }
}