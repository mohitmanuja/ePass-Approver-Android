package com.anumati.approver.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.anumati.approver.activities.DeeplinkActivity
import com.anumati.approver.utils.CommonUtils.Companion.isNotNull

object DeeplinkUtils {


    fun openInBrowser(url: String, context: Context) {
        var webPage = Uri.parse(url)
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            webPage = Uri.parse("http://$url")
        }
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }


    fun rateApp(context: Context) {
        try {
            val rateIntent = Intent(Intent.ACTION_VIEW)
            rateIntent.data =
                Uri.parse("market://details?id=" + context.packageName)
            context.startActivity(rateIntent)
        } catch (e: Exception) {
            context.showToast("You don't have play store :) Thanks. ")
        }
    }


    fun promoApp(id: String?, message: String?, context: Context) {
        if (isAppInstalled("com.android.vending", context)) {
            val rateintent = Intent(Intent.ACTION_VIEW)
            rateintent.data = Uri.parse("market://details?id=$id")
            context.startActivity(rateintent)
            if (isNotNull(message)) {
                context.showToast(message!!)
            }
        } else {
            val i = Intent(context, DeeplinkActivity::class.java)
            context.startActivity(i)
        }
    }

    fun isAppInstalled(packageName: String?, context: Context): Boolean {
        val mIntent =
            context.packageManager.getLaunchIntentForPackage(packageName!!)
        return mIntent != null
    }

}