package com.anumati.approver.utils

import android.os.Bundle
import com.crashlytics.android.Crashlytics
import com.anumati.approver.BuildConfig
import com.google.firebase.analytics.FirebaseAnalytics
import java.lang.Exception

object AnalyticsUtil {


        fun logExecption(e: Exception) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace()

            } else {
                Crashlytics.logException(e)
            }
        }
        fun logEvent(firebaseAnalytics:FirebaseAnalytics){
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "test")
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "clicked")
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
        }
}