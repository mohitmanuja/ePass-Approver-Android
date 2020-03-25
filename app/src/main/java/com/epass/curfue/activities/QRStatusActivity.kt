package com.epass.curfue.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityQrStatusBinding
import com.epass.curfue.models.VerifyTokenResponse
import com.epass.curfue.utils.CommonUtils
import com.epass.curfue.utils.showToast

class QRStatusActivity :AppCompatActivity(){

    lateinit var binding:ActivityQrStatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qr_status)

        val name = intent?.getStringExtra("name")
        val age = intent?.getStringExtra("age")
        val aadhar = intent?.getStringExtra("aadhar")
        val applicationID = intent?.getStringExtra("applicationID")
        binding.nameText.text = name
        binding.ageText.text = age
        binding.aadhaarText.text = aadhar
        binding.qrCodeText.text = applicationID


    }
}