package com.anumati.approver.utils

import com.anumati.approver.BuildConfig

object AnalyticsUtil {

    fun logException(e: Exception) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace()
        } else {
        }
    }

}