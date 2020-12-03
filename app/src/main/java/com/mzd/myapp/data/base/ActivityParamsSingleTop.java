package com.mzd.myapp.data.base;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class contains the parameters to launch an activity that should not be instantiated multiple times
 */
public class ActivityParamsSingleTop extends ActivityParams{

    public ActivityParamsSingleTop(Class<? extends AppCompatActivity> activity) {
        this(activity,
                null,
                0);
    }

    @SuppressWarnings("unused, WeakerAccess")
    public ActivityParamsSingleTop(Class<? extends AppCompatActivity> activity, String params) {
        this(activity,
                params,
                0);
    }

    @SuppressWarnings("unused, WeakerAccess")
    public ActivityParamsSingleTop(Class<? extends AppCompatActivity> activity, String params, int flags) {
        super(activity,
                params,
                flags + Intent.FLAG_ACTIVITY_CLEAR_TOP + Intent.FLAG_ACTIVITY_SINGLE_TOP);
    }


}
