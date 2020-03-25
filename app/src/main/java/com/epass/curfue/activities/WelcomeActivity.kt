package com.epass.curfue.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityWelcomeBinding
import com.epass.curfue.utils.CommonUtils
import com.epass.curfue.utils.showToast

class WelcomeActivity :BaseActivity(){

    lateinit var binding:ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_welcome)

        binding.login.setOnClickListener {
            if(CommonUtils.isNotNull(binding.etPh.text.toString()) && binding.etPh.text.toString().length==12){
                val mobileNumber = binding.etPh.text.toString()
                val intent = Intent(this, EnterOtpActivity::class.java)
                intent.putExtra(CommonUtils.NUMBER,mobileNumber)
                startActivity(intent)
            }else{
                showToast("Please enter 12 digit Aadhaar.")
            }
        }
    }
}