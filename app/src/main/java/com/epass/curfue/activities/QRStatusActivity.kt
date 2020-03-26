package com.epass.curfue.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityQrStatusBinding
import com.epass.curfue.models.TokenStatus


class QRStatusActivity :AppCompatActivity(){

    lateinit var binding:ActivityQrStatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qr_status)

        val name = intent?.getStringExtra("name")
        val age = intent?.getIntExtra("age",0)
        val aadhar = intent?.getStringExtra("aadhar")
        val applicationID = intent?.getLongExtra("applicationID",-1)
        val status = intent?.getSerializableExtra("status")
        binding.nameText.text = name
        binding.ageText.text = age.toString()
        binding.aadhaarText.text = aadhar
        binding.qrCodeText.text = applicationID.toString()
        if (status == TokenStatus.APPROVED){
            binding.qrCodeStatusLabel.setText("QR code verified succesfully!")
            binding.statusIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.activated))
        }else{
            binding.qrCodeStatusLabel.setText("Oops! Seems like an expired code!")
            binding.statusIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.expired))
        }
        binding.done.setOnClickListener {
            val myIntent = Intent(this, MultiTrackerActivity::class.java)
            myIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(myIntent)
        }



    }
}