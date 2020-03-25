package com.epass.curfue.notifications


import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.epass.curfue.activities.DeeplinkActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.epass.curfue.utils.CommonUtils
import java.net.HttpURLConnection
import java.net.URL


class OurFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val data = remoteMessage.data
        val extras = getBundleFromMap(data)
        if (!extras.isEmpty) {
            Log.e("save", extras.toString())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                buildNotification(extras)
            }
        } else {
            Log.e("save", "empty")
        }
    }


    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("token", p0)
    }

    private fun getBundleFromMap(map: Map<String, String>?): Bundle {
        val bundle = Bundle()
        if (map != null) {
            for (key in map.keys) {
                val value = map[key]
                bundle.putString(key, value)
            }
        }
        return bundle
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun buildNotification(messageObject: Bundle) {
        val title = messageObject.getString("title", "")
        val text = messageObject.getString("text", "")
        val url = messageObject.getString("deep_link", "")
        val imageUri = messageObject.getString("image")
        val bitmap = getBitmapFromUrl(imageUri)
        

        val routeIntent = Intent(this, DeeplinkActivity::class.java)
        routeIntent.putExtra("deep_link", url)
        routeIntent.putExtra("title", title)
        routeIntent.putExtra("text", text)
        routeIntent.putExtra("image", imageUri)
        val contentIntent = PendingIntent.getActivity(this, 0,
                routeIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val mBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, CustomNotificationChannels.DEFAULT)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(android.R.drawable.sym_def_app_icon)
                    .setAutoCancel(true)
                    .setContentIntent(contentIntent)
        } else {
            Notification.Builder(this, CustomNotificationChannels.DEFAULT)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(android.R.drawable.sym_def_app_icon)
                    .setAutoCancel(true)
                    .setContentIntent(contentIntent)
        }
        if (bitmap != null) {
            mBuilder.setLargeIcon(bitmap)
            mBuilder.setStyle(Notification.BigPictureStyle()
                    .bigPicture(bitmap))/*Notification with Image*/
        }

        val mNotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, mBuilder.build())

    }

    private fun getBitmapFromUrl(imageUrl: String?): Bitmap? {
        if (CommonUtils.isNotNull(imageUrl)) {
            try {
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.setDoInput(true)
                connection.connect()
                val input = connection.getInputStream()
                return BitmapFactory.decodeStream(input)

            } catch (e: Exception) {
//                AnalyticsUtil.logExecption(e)
                return null
            }
        }
        return null
    }
}