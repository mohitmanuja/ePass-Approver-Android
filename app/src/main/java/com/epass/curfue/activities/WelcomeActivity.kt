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
            if(CommonUtils.isNotNull(binding.etPh.text.toString()) && binding.etPh.text.toString().length==10){
                startActivity(Intent(this,EnterOtpActivity::class.java))
            }else{
                showToast("Please enter 10 digit mobile number.")
            }
        }
    }
}