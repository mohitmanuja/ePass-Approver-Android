package com.anumati.approver.onBoarding.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.anumati.approver.R
import com.anumati.approver.activities.BaseActivity
import com.anumati.approver.qrcodecheck.activities.QRCodeScannerActivity
import com.anumati.approver.databinding.ActivityOnboardingBinding
import com.anumati.approver.utils.SharedPrefHelper

class OnBoardingActivity : BaseActivity() {
    lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIfUserLogin()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        setObservers()
    }

    private fun setObservers() {
        binding.getStarted.setOnClickListener {
            startActivity(Intent(this, EnterMobileNumberActivity::class.java))
        }
    }

    private fun checkIfUserLogin(){
        if (SharedPrefHelper.isUserLogin(this)){
            finish()
            startActivity(Intent(this, QRCodeScannerActivity::class.java))
        }
    }

}