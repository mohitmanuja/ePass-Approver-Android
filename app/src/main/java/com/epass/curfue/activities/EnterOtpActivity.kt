package com.epass.curfue.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityEnterOtpBinding
import com.epass.curfue.databinding.ActivityWelcomeBinding
import com.epass.curfue.utils.CommonUtils
import com.epass.curfue.utils.showToast

class EnterOtpActivity : BaseActivity() {

    lateinit var binding: ActivityEnterOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_enter_otp)
        val mobileNumber = intent.getStringExtra(CommonUtils.NUMBER)
        binding.otpSentText.text = "Enter OTP sent to $mobileNumber"
        supportActionBar?.title = "Enter OTP"
        binding.login.setOnClickListener {
            if (CommonUtils.isNotNull(binding.otpEditText.text.toString())) {
                startActivity(Intent(this, ProfileActivity::class.java))

            } else {
                showToast("Please enter otp")
            }
        }
    }
}