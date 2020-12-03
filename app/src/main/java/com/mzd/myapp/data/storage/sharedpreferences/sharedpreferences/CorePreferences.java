package com.mzd.myapp.data.storage.sharedpreferences.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

/**
 * Created by cdebast on 30/01/2018.
 */

public class CorePreferences {

    private static CorePreferences instance = null;
    private final SharedPreferences defaultSharedPreferences;

    CorePreferences(final Context context) {
        defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static CorePreferences getInstance(final Context context) {
        if (instance == null) {
            instance = new CorePreferences(context);
        }

        return instance;
    }


    public static void reset(Context context) {
        getInstance(context).defaultSharedPreferences
                .edit()
                .clear()
                .apply();
    }

    public static void setAndApply(final Context context, @SuppressWarnings("SameParameterValue") final String key, final String value) {
        getInstance(context).defaultSharedPreferences.edit().putString(key, value).apply();
    }


    public static String getString(final Context context, @SuppressWarnings("SameParameterValue") final String key) {
        return getInstance(context).defaultSharedPreferences.getString(key, null);
    }

    @SuppressWarnings("unused")
    public static void setAndApply(final Context context, final String key, final long value) {
        getInstance(context).defaultSharedPreferences.edit().putLong(key, value).apply();
    }

    @SuppressWarnings("unused")
    public static long getLong(final Context context, final String key) {
        return getInstance(context).defaultSharedPreferences.getLong(key, 0L);
    }

    @SuppressWarnings("unused")
    public static void setAndApply(final Context context, final String key, final Date value) {
        if (value != null) {
            getInstance(context).defaultSharedPreferences.edit().putLong(key, value.getTime()).apply();
        } else {
            getInstance(context).defaultSharedPreferences.edit().putLong(key, 0).apply();
        }
    }


    public static Date getDate(final Context context, final String key) {
        final long dateTime = getInstance(context).defaultSharedPreferences.getLong(key, 0);
        return dateTime == 0 ? null : new Date(dateTime);
    }
}
