package com.anumati.approver.onBoarding.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anumati.approver.R
import com.anumati.approver.activities.BaseActivity
import com.anumati.approver.qrCodeCheck.activities.QRCodeScannerActivity
import com.anumati.approver.databinding.ActivityVerifyOtpBinding
import com.anumati.approver.onBoarding.repos.OnBoardingRepo
import com.anumati.approver.utils.CommonUtils
import com.anumati.approver.utils.showToast
import com.anumati.approver.onBoarding.viewmodels.OnBoardingViewModel
import com.anumati.approver.onBoarding.viewmodels.OnBoardingViewModelFactory

class VerifyOtpActivity : BaseActivity() {
    lateinit var binding: ActivityVerifyOtpBinding
    lateinit var number:String

    private val onBoardingViewModelFactory: OnBoardingViewModelFactory by lazy {
        OnBoardingViewModelFactory(
            application,
            OnBoardingRepo()
        )
    }
    private val onBoardingViewModel: OnBoardingViewModel by lazy {
        ViewModelProvider(this, onBoardingViewModelFactory).get(OnBoardingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify_otp)
        number = intent?.getStringExtra(CommonUtils.NUMBER)?:""
        val isoCode = intent?.getStringExtra(CommonUtils.ISO_CODE)?:""
        onBoardingViewModel.phoneNumber = number
        onBoardingViewModel.isoCode = isoCode
        binding.phoneDescription.text = getString(R.string.enter_the_otp_sent_to_number,number)

        setListeners()
        setObservers()

    }

    private fun setObservers() {
        onBoardingViewModel.getVerifyOTPRequestLiveData().observe(this, Observer {
            val intent = Intent(this,
                QRCodeScannerActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        })

        onBoardingViewModel.getshowToastLiveData().observe(this, Observer {
            showToast(it)
        })

        onBoardingViewModel.getUpdateScreenLiveData().observe(this, Observer {
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
        onBoardingViewModel.getLoadingScreen().observe(this, Observer {
            if (it) {
                showProgressDialog(getString(R.string.processing))
            } else {
                dismissProgressDialog()
            }

        })
        onBoardingViewModel.getCreateOTPRequestLiveData().observe(this, Observer { requestSent->
            if (requestSent){
               showToast(getString(R.string.otp_resent))
            }
        })
    }

    private fun setListeners() {
        binding.otpEditText.requestFocus()
        binding.verify.setOnClickListener {
            hideKeyboard(this)
            onBoardingViewModel.otpEntered(binding.otpEditText.text.toString())
        }

        binding.resendOtp.setOnClickListener {
            onBoardingViewModel.createOTPRequest(number)
        }
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

    }

}