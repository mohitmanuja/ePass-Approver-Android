package com.epass.curfue.utils

import android.content.Context

class SharedPrefHelper {
    companion object {
        const val USER_PREF = "USER_PREF"
        const val PREF_IS_FIRST_TIME = "PREF_IN_FIRST_TIME"
        const val PREF_RATE_SHOW_TIME = "PREF_RATE_SHOW_TIME"

        @JvmStatic
        fun isFirstTime(context: Context): Boolean {
            val smsPrefs = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            return smsPrefs.getBoolean(PREF_IS_FIRST_TIME, true)

        }

        fun saveFirstTimeDone(context: Context) {
            val smsPrefs = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            smsPrefs.edit().putBoolean(PREF_IS_FIRST_TIME, false).apply()
        }

        @JvmStatic
        fun alreadyShown(context: Context): Boolean {
            val smsPrefs = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            return smsPrefs.getBoolean(PREF_RATE_SHOW_TIME, false)

        }

        fun saveAlreadyShown(context: Context) {
            val smsPrefs = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            smsPrefs.edit().putBoolean(PREF_RATE_SHOW_TIME, true).apply()
        }
    }


}