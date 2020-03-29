package com.anumati.approver.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.anumati.approver.R
import com.anumati.approver.databinding.ActivityOnboardingBinding
import com.anumati.approver.databinding.ActivityVerifyOtpBinding
import com.anumati.approver.utils.CommonUtils
import com.anumati.approver.utils.showToast

class VerifyOtpActivity : BaseActivity() {
    lateinit var binding: ActivityVerifyOtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify_otp)
        val number = intent?.getStringExtra(CommonUtils.NUMBER)
        binding.phoneDescription.text = "Enter the OTP sent to $number"
        binding.verify.setOnClickListener {
            startActivity(Intent(this,
                QRCodeScannerActivity::class.java))
        }

        binding.resendOtp.setOnClickListener {
            showToast("Call resend API")
        }

    }

}