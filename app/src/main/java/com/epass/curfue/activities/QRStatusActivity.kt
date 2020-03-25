package com.epass.curfue.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityQrStatusBinding

class QRStatusActivity :BaseActivity(){

    lateinit var binding:ActivityQrStatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qr_status)


    }


}