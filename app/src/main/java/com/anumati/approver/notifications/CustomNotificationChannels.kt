package com.anumati.approver.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

object CustomNotificationChannels {

    const val DEFAULT = "default_channel"

    fun createNotificationChannelsCompat(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannels(context)
    }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannels(context: Context) {
        val channel = NotificationChannel(
            DEFAULT, "Default",
                NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = context.getSystemService(
                NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel)
    }
}