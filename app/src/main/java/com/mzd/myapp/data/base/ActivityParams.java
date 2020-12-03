package com.mzd.myapp.data.base;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class contains the regular parameters to launch an activity. If you want the activity
 * be be single in the stack, please use {@link ActivityParamsSingleTop}
 */
public class ActivityParams {
    public int uid;
    final Class<? extends AppCompatActivity> activity;
    public String params;
    @SuppressWarnings("WeakerAccess")
    protected final int flags;

    @SuppressWarnings("unused")
    public ActivityParams(Class<? extends AppCompatActivity> activity) {
        this(activity, null);
    }

    @SuppressWarnings("WeakerAccess")
    public ActivityParams(Class<? extends AppCompatActivity> activity, String params) {
        this(activity, params, 0);
    }

    @SuppressWarnings("WeakerAccess")
    public ActivityParams(Class<? extends AppCompatActivity> activity, String params, int flags) {
        this.activity = activity;
        this.params = params;
        this.flags = flags;
    }

    public Class<? extends AppCompatActivity> getActivity() {
        return activity;
    }

    public String getParams() {
        return params;
    }

    public int getFlags() {
        return flags;
    }
}
