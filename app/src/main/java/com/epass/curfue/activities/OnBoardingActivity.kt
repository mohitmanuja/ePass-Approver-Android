package com.epass.curfue.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityOnboardingBinding

class OnBoardingActivity : BaseActivity() {
    lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        binding.getStarted.setOnClickListener {
            startActivity(Intent(this, EnterMobileNumberActivity::class.java))
        }
    }

}