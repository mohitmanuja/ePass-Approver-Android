package com.anumati.approver.utils


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


class CommonUtils {
    companion object {

        const val NUMBER: String = "number"
        const val ISO_CODE: String = "iso"
        const val DATA: String = "data"

        @JvmStatic
        fun isNotNull(string: String?): Boolean {
            return !(string == null || string.isEmpty())
        }

        @JvmStatic
        fun isNull(string: String?): Boolean {
            return (string == null || string.isEmpty())
        }

        fun isInternetAvailable(context: Context): Boolean {
            var result = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val actNw =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
                result = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else {
                connectivityManager.run {
                    connectivityManager.activeNetworkInfo?.run {
                        result = when (type) {
                            ConnectivityManager.TYPE_WIFI -> true
                            ConnectivityManager.TYPE_MOBILE -> true
                            ConnectivityManager.TYPE_ETHERNET -> true
                            else -> false
                        }

                    }
                }
            }

            return result
        }

    }
}