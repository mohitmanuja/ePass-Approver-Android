package com.epass.curfue.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityCreateApplicationBinding

class CreateApplicationActivity :BaseActivity(){

    lateinit var binding:ActivityCreateApplicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_create_application)
        supportActionBar?.title = "Request Anumati"

        binding.submit.setOnClickListener {
            if (validateFields()){

                startActivity(Intent(this,ApplicationCreatedActivity::class.java))
            }
        }

    }

    private fun validateFields(): Boolean {

        return true
    }
}