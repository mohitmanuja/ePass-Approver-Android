package com.epass.curfue.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityEnterQrCodeBinding

class QRVerificationActivity :BaseActivity(){

    lateinit var binding:ActivityEnterQrCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_enter_qr_code)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

    }


}