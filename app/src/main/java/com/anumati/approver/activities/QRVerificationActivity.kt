package com.anumati.approver.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anumati.approver.R
import com.anumati.approver.databinding.ActivityEnterQrCodeBinding
import com.anumati.approver.repos.TokenRepo
import com.anumati.approver.utils.CommonUtils
import com.anumati.approver.utils.showToast
import com.anumati.approver.viewmodels.TokenViewModelFactory
import com.anumati.approver.viewmodels.VerifyTokenViewModel

class QRVerificationActivity : BaseActivity() {

    lateinit var binding: ActivityEnterQrCodeBinding
    private val tokenViewModelFactory = TokenViewModelFactory(TokenRepo())
    private val tokenViewModel: VerifyTokenViewModel by lazy {
        ViewModelProvider(this, tokenViewModelFactory).get(VerifyTokenViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_enter_qr_code)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        setScreenName("QR Verification Screen")
        binding.verify.setOnClickListener {
            if (validateToken()) {
                tokenViewModel.fetchTokenResponse(binding.qrCodeEdittext.text.toString(), this)
            } else {
                showToast(getString(R.string.please_enter_6_digit_token_number))
            }
        }
        setObservers()
    }

    private fun setObservers() {

        tokenViewModel.getshowToastLiveData().observe(this, Observer {
            showToast(it)
        })

        tokenViewModel.getUpdateScreenLiveData().observe(this, Observer {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle(getString(R.string.request_failed))
            alertDialogBuilder
                .setMessage(it)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.try_again)
                ) { dialog, _ -> dialog.dismiss() }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        })
        tokenViewModel.getLoadingScreen().observe(this, Observer {
            if (it) {
                showProgressDialog(getString(R.string.processing))
            } else {
                dismissProgressDialog()
            }

        })
        tokenViewModel.getTokenResponseLiveData().observe(this, Observer {response->
            if(response!=null){
                val intentNew = Intent(this, QRStatusActivity::class.java)
                intentNew.putExtra(CommonUtils.DATA,response)
                startActivity(intentNew)
            }else{
                showToast(getString(R.string.something_went_wrong_try_again))
            }
        })

    }

    private fun validateToken(): Boolean {
        return binding.qrCodeEdittext.text.toString().length == 6
    }


}