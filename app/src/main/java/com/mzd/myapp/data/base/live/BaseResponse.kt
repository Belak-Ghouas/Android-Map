package com.mzd.myapp.data.base.live

import com.google.gson.annotations.Expose
import com.mzd.myapp.data.base.mocks.Header

class BaseResponse<T> {
    @Expose
    var header: Header? = null
    @Expose
    var body: T? = null
}