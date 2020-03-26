package com.epass.curfue.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityEnterQrCodeBinding
import com.epass.curfue.repos.TokenRepo
import com.epass.curfue.utils.showToast
import com.epass.curfue.viewmodels.VerifyTokenViewModel
import com.epass.curfue.viewmodels.TokenViewModelFactory

class QRVerificationActivity : BaseActivity() {

    lateinit var binding: ActivityEnterQrCodeBinding
    private val tokenViewModelFactory = TokenViewModelFactory(TokenRepo(this))
    private val tokenViewModel: VerifyTokenViewModel by lazy {
        ViewModelProvider(this, tokenViewModelFactory).get(VerifyTokenViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_enter_qr_code)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        binding.verify.setOnClickListener {
            if (validateToken()) {
                tokenViewModel.fetchTokenResponse(binding.qrCodeEdittext.text.toString(), this)
            } else {
                showToast("Please enter 6 digit valid token number.")
            }
        }
        setObservers()
    }

    private fun setObservers() {

        tokenViewModel.getUpdateScreenLiveData().observe(this, Observer {
            this.showToast(it)
        })
        tokenViewModel.getLoadingScreen().observe(this, Observer {
            if (it) {
                showProgressDialog("Processing")
            } else {
                dismissProgressDialog()
            }

        })
        tokenViewModel.getTokenResponseLiveData().observe(this, Observer {

            val intentNew = Intent(this, QRStatusActivity::class.java)
            intentNew.putExtra("name", it.additionalAttributes?.issuedToname)
            intentNew.putExtra("age", it.age)
            intentNew.putExtra("aadhar", it.adhaarID)
            intentNew.putExtra("applicationID", it.applicationID)
            intentNew.putExtra("status", it.status)
            startActivity(intentNew)
        })

    }

    private fun validateToken(): Boolean {
        return binding.qrCodeEdittext.text.toString().length == 6
    }


}