package com.anumati.approver.utils

import android.content.Context

class SharedPrefHelper {
    companion object {
        const val USER_PREF = "USER_PREF"
        const val IS_USER_LOGIN = "PREF_IN_FIRST_TIME"
        const val AUTH_TOKEN = "AUTH_TOKEN"
        const val PUBLIC_KEY = "PUBLIC_KEY"


        fun saveAuthToken(context: Context, authToken: String) {
            val userPrefs = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            userPrefs.edit().putString(AUTH_TOKEN, authToken).apply()
        }

        fun savePublicKey(context: Context, publicKey: String) {
            val userPrefs = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            userPrefs.edit().putString(PUBLIC_KEY, publicKey).apply()
        }

        @JvmStatic
        fun getPublicKey(context: Context): String? {
            val userPrefs = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            return userPrefs.getString(PUBLIC_KEY, null)

        }

        @JvmStatic
        fun getAuthKey(context: Context): String? {
            val userPrefs = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            return userPrefs.getString(AUTH_TOKEN, null)

        }

        @JvmStatic
        fun isUserLogin(context: Context): Boolean {
            val userPrefs = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            return userPrefs.getBoolean(IS_USER_LOGIN, false)

        }
        @JvmStatic
        fun saveUserLoggedIn(context: Context) {
            val userPrefs = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)            
            userPrefs.edit().putBoolean(IS_USER_LOGIN, true).apply()
        }

    }


}