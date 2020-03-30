package com.anumati.approver.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast


fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.logMsg(msg: String) {
    Log.e("check", msg)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Context.shareMsgIntent(msg: String, header: String) {
    val shareIntent = Intent()
    shareIntent.action = Intent.ACTION_SEND
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_TEXT, msg);
    this.startActivity(Intent.createChooser(shareIntent, header))
}


