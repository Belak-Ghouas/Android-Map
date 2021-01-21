package com.mzd.myapp.data.base

import androidx.appcompat.app.AppCompatActivity

open class ActivityParams(activity: Class<out AppCompatActivity?>?, params: String?) {

    var params: String?=params
    var activity: Class<out AppCompatActivity?>? = activity

    constructor (activity: Class<out AppCompatActivity?>?):this(activity, null){
    }
}