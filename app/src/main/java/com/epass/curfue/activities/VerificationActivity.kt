package com.epass.curfue.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActvityVerificationBinding

class VerificationActivity :BaseActivity(){

    lateinit var binding:ActvityVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.actvity_verification)
        supportActionBar?.title = "Verify Anumati"
        binding.verify.setOnClickListener {
            startActivity(Intent(this, VerificationFoundActivity::class.java))
        }

    }


}