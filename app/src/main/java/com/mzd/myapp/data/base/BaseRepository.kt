package com.mzd.myapp.data.base

import com.mzd.myapp.data.base.live.BaseServiceCall
import com.mzd.myapp.data.base.mocks.MockServiceConfig

abstract class BaseRepository<R : BaseServiceCall>(val serviceCall: R) {

    abstract fun drop()

    fun setMockMode(mockMode: MockServiceConfig.MockMode) {
        serviceCall.mockServiceConfig?.mockMode = mockMode
    }

    interface BaseRepositoryResponse {
        fun onAny()
    }
}