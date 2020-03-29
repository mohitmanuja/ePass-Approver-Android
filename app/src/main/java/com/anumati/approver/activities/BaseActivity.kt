package com.anumati.approver.activities

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.view.inputmethod.InputMethodManager
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

    fun showProgressDialog(msg: String) {
        loadingDialog = SpotsDialog.Builder()
            .setContext(this)
            .setMessage(msg)
            .setCancelable(false)
            .build()
            .apply {
                show()
            }
    }

    fun dismissProgressDialog() {
        if (loadingDialog?.isShowing == true) {
            loadingDialog?.dismiss()
        }
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

    override fun onStop() {
        if (loadingDialog?.isShowing== true){
            loadingDialog?.dismiss()
        }
        super.onStop()
    }
}