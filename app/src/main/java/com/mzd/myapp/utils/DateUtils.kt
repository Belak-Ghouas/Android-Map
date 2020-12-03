package com.mzd.myapp.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class DateUtils {

    companion object {
        /**
         * Converts a string formatted like 2018-02-01T00:00:00+01:00 to a date.
         */
        fun stringToDate(input: String?): Date? {
            return if (input != null) {
                var d: Date? = null
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                try {
                    d = sdf.parse(input)
                } catch (ex: ParseException) {
                    Log.v("Exception", ex.message!!)
                }

                d
            } else {
                null
            }
        }

        fun nbDaysFromNow(date: Date?): Long {
            return if (date != null) {
                val now = Date()

                val diff: Long = date.time - now.time

                TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
            } else {
                0
            }
        }
    }
}

