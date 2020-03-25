package com.epass.curfue.activities

import android.content.Intent
import android.os.Bundle
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityApplicationCreatedBinding
import com.epass.curfue.databinding.ActivityWelcomeBinding
import com.epass.curfue.utils.CommonUtils
import com.epass.curfue.utils.showToast

class CreateApplicationActivity :BaseActivity(){

    lateinit var binding:ActivityApplicationCreatedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

    }
}