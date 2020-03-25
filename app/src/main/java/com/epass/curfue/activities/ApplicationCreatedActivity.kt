package com.epass.curfue.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityApplicationCreatedBinding
import com.epass.curfue.databinding.ActivityCreateApplicationBinding

class ApplicationCreatedActivity :BaseActivity(){

    lateinit var binding:ActivityApplicationCreatedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_application_created)
        supportActionBar?.title = "Anumati Granted"


    }


}