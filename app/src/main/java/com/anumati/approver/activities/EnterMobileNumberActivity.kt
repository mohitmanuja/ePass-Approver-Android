package com.anumati.approver.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anumati.approver.R
import com.anumati.approver.databinding.ActivityGetOtpBinding
import com.anumati.approver.repos.OnboardingRepo
import com.anumati.approver.utils.CommonUtils
import com.anumati.approver.utils.showToast
import com.anumati.approver.viewmodels.OnboardingViewModel
import com.anumati.approver.viewmodels.OnboardingViewModelFactory

class EnterMobileNumberActivity : BaseActivity() {
    lateinit var binding: ActivityGetOtpBinding
    private val onBoardingViewModelFactory: OnboardingViewModelFactory by lazy {
        OnboardingViewModelFactory(application, OnboardingRepo())
    }
    private val onBoardingViewModel: OnboardingViewModel by lazy {
        ViewModelProvider(this, onBoardingViewModelFactory).get(OnboardingViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_get_otp)
        binding.lifecycleOwner = this


        setListeners()
        setObservers()
    }

    private fun setObservers() {
        onBoardingViewModel.getCreateOTPRequestLiveData().observe(this, Observer { requestSent ->
            if (requestSent) {
                val intent = Intent(this, VerifyOtpActivity::class.java)
                intent.putExtra(CommonUtils.NUMBER, binding.mobileNumberEditText.text.toString())
                startActivity(intent)
            }
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
                .setPositiveButton(
                    getString(R.string.try_again)
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
    }

    private fun setListeners() {
        binding.getOtp.setOnClickListener {
            hideKeyboard(this)
            onBoardingViewModel.phoneNumberEntered(binding.mobileNumberEditText.text.toString())
        }
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

}