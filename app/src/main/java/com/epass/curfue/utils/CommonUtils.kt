package com.epass.curfue.utils


import android.app.Activity
import android.content.Intent
import android.net.Uri
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class CommonUtils {
    companion object {

        const val NUMBER: String = "number"

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


        @JvmStatic
        fun getInitialText(word: String): Char {
            return word.get(0)
        }

 /*       @JvmStatic
        fun setBackNavigationToolbar(context: Activity, mActionBar: Toolbar) {

            mActionBar.setNavigationIcon(ContextCompat.getDrawable(context, R.drawable.ic_keyboard_arrow_left_white_24dp))

            mActionBar.setNavigationOnClickListener {
                context.onBackPressed()
            }
        }*/




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


    }



}