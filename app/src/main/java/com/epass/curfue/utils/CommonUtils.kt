package com.epass.curfue.utils


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class CommonUtils {
    companion object {

        const val NUMBER: String = "number"
        const val DATA: String = "data"

        @JvmStatic
        fun isNotNull(string: String?): Boolean {
            return !(string == null || string.isEmpty())
        }

        @JvmStatic
        fun isNull(string: String?): Boolean {
            return (string == null || string.isEmpty())
        }

        @JvmStatic
        fun makeArrayList(jsonArray: JSONArray?): ArrayList<JSONObject> {
            val listdata = ArrayList<JSONObject>()
            val jArray = jsonArray as JSONArray
            for (i in 0 until jArray.length()) {
                listdata.add(jArray.getJSONObject(i))
            }
            return listdata
        }

        @JvmStatic
        fun getStringFromDate(long: Long, outputFromat: String): String? {
            val date = Date(long)
            val df2 = SimpleDateFormat(outputFromat, Locale.ENGLISH)
            return df2.format(date)
        }


        fun sendEmail(context: Activity,recipient: String, subject: String, message: String) {
            val mIntent = Intent(Intent.ACTION_SEND)
            mIntent.data = Uri.parse("mailto:")
            mIntent.type = "text/plain"
            mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            mIntent.putExtra(Intent.EXTRA_TEXT, message)
            try {
                context.startActivity(Intent.createChooser(mIntent, "Choose Email..."))
            }
            catch (e: Exception){
            }

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