package com.anumati.approver.utils

import com.anumati.approver.BuildConfig
import com.crashlytics.android.Crashlytics

object AnalyticsUtil {

    fun logException(e: Exception) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace()
        } else {
            Crashlytics.logException(e)
        }
    }

}