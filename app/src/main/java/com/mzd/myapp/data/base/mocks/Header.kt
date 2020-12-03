package com.mzd.myapp.data.base.mocks

import com.google.gson.annotations.Expose

class Header {
    @Expose
    var code = 0
    @Expose
    var message: String? = null
}