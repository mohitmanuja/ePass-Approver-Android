package com.epass.curfue.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityGetOtpBinding
import com.epass.curfue.utils.CommonUtils
import com.epass.curfue.utils.showToast

class EnterMobileNumberActivity :BaseActivity(){
    lateinit var binding: ActivityGetOtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_get_otp)

        binding.getOtp.setOnClickListener {
            if (binding.mobileNumberEditText.text.toString().length==10){
                // todo add API
                val intent = Intent(this,VerifyOtpActivity::class.java)
                intent.putExtra(CommonUtils.NUMBER,binding.mobileNumberEditText.text.toString())
                startActivity(intent)
            }else{
                showToast("Please enter valid 10 digit mobile number")
            }

        }
    }

}