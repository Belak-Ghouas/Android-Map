package com.mzd.myapp.data.base.mocks

import com.google.gson.annotations.SerializedName

class FileRule {
    @SerializedName("keys")
    var keyList: List<String>? = null
    var file: String? = null

}