package com.epass.curfue.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.epass.curfue.MainActivity
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityProfileBinding
import com.epass.curfue.databinding.ActivityWelcomeBinding
import com.epass.curfue.utils.CommonUtils
import com.epass.curfue.utils.showToast

class ProfileActivity :BaseActivity(){

    lateinit var binding:ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile)
        supportActionBar?.title = "Create Profile"

        binding.sumbit.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}