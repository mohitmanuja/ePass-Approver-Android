package com.anumati.approver.notifications


import android.content.Context
import android.net.Uri
import com.anumati.approver.utils.CommonUtils
import com.anumati.approver.utils.DeeplinkUtils


object DeepLinkHandleProvider {
    init {

    }

    fun openThisUrl(url: String?,context: Context) {
        if (CommonUtils.isNotNull(url)){
            parseUrl(url!!,context)
        }
    }

    private fun parseUrl(url: String, context: Context) {
        var splitResult: Array<String>?
        val urlToSplit = url.replace("http://", "").replace("https://", "")
        splitResult = urlToSplit.split("[/]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var lastPart: String? = null
        var pageType: String? = null
        if (splitResult.size > 1) {
            pageType = splitResult[1]
            lastPart = splitResult[splitResult.size - 1]
        } else if (splitResult.size == 1) {
            //  return;
        }
        var eid: String? = null
        if (lastPart != null) {
            val array = lastPart.split("[#!?]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (array.isNotEmpty()) {
                eid = array[0]
            }
        }
        openNotificationPage(pageType, eid, url,context)
    }

    private fun openNotificationPage(
        pageType: String?,
        eid: String?,
        url: String,
        context: Context
    ) {
        val uri = Uri.parse(url)
        val inWebview = uri.getBooleanQueryParameter("in_web_view", false)
        val inChrome = uri.getBooleanQueryParameter("in_chrome", false)
        val name = uri.getQueryParameter("name")

        if (inChrome){
            DeeplinkUtils.openInBrowser(url,context)
            return
        }


        if ("rateus" == pageType) {
            DeeplinkUtils.rateApp(context)
            return
        }


        DeeplinkUtils.openInBrowser(url,context)
    }
}


