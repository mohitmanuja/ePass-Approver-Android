package com.anumati.approver.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object DeepLinkUtils {


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

}