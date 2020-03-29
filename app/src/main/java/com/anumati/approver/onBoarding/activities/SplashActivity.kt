package com.anumati.approver.onBoarding.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.anumati.approver.R
import com.anumati.approver.qrcodecheck.activities.QRCodeScannerActivity
import com.anumati.approver.utils.SharedPrefHelper

class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT: Long = 1000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            if (SharedPrefHelper.isUserLogin(this)) {
                startActivity(Intent(this, QRCodeScannerActivity::class.java))
            } else {
                startActivity(Intent(this, OnBoardingActivity::class.java))

            }

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}