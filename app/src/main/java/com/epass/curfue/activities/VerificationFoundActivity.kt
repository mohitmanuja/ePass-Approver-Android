package com.epass.curfue.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActvityVerificationFoundBinding

class VerificationFoundActivity :BaseActivity(){

    lateinit var binding:ActvityVerificationFoundBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.actvity_verification_found)
        supportActionBar?.title = "Anumati Verfied"


    }


}