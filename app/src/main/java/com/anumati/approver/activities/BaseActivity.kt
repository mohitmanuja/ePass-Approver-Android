package com.anumati.approver.activities

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import dmax.dialog.SpotsDialog

open class BaseActivity : AppCompatActivity(){
    private var loadingDialog: AlertDialog? = null
    private val firebaseAnalytics : FirebaseAnalytics by lazy {
        FirebaseAnalytics.getInstance(this)
    }

    fun setScreenName(screenName:String){
        firebaseAnalytics.setCurrentScreen(this, screenName, null)
    }

    fun showProgressDialog(msg:String) {
       if (loadingDialog == null){
           loadingDialog = SpotsDialog.Builder()
               .setContext(this)
               .setMessage(msg)
               .setCancelable(false)
               .build()
               .apply {
                   show()
               }
       }
    }

    fun dismissProgressDialog() {
        if (loadingDialog?.isShowing == true) {
            loadingDialog?.dismiss()
        }
    }

    override fun onStop() {
        if (loadingDialog?.isShowing== true){
            loadingDialog?.dismiss()
        }
        super.onStop()
    }
}