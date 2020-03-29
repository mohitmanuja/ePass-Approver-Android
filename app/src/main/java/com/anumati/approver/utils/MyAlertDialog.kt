package com.anumati.approver.utils

import android.app.AlertDialog
import android.content.Context

class MyAlertDialog {

    fun showAlertDialog(dialogBuilder: AlertDialog.Builder.() -> Unit, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.dialogBuilder()
        val dialog = builder.create()

        dialog.show()
    }

    fun AlertDialog.Builder.positiveButton(text: String = "Okay", handleClick: (which: Int) -> Unit = {}) {
        this.setPositiveButton(text) { _, which-> handleClick(which) }
    }

    fun AlertDialog.Builder.negativeButton(text: String = "Cancel", handleClick: (which: Int) -> Unit = {}) {
        this.setNegativeButton(text) { _, which-> handleClick(which) }
    }
}