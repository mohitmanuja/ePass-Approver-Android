package com.anumati.approver.qrCodeCheck.activities

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.anumati.approver.R
import com.anumati.approver.activities.BaseActivity
import com.anumati.approver.databinding.ActivityQrStatusBinding
import com.anumati.approver.onBoarding.models.ApplicationStatus
import com.anumati.approver.onBoarding.models.ApplicationType
import com.anumati.approver.onBoarding.models.TokenVerifyResponse
import com.anumati.approver.utils.CommonUtils
import com.anumati.approver.views.KeyValueView


class QRStatusActivity : BaseActivity() {

    lateinit var binding: ActivityQrStatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qr_status)
        setScreenName("QR Status Activity")
        val tokenVerifyResponse: TokenVerifyResponse =
            intent?.getSerializableExtra(CommonUtils.DATA) as TokenVerifyResponse

        val status: ApplicationStatus = tokenVerifyResponse.applicationStatus
        val applicationType: ApplicationType = tokenVerifyResponse.applicationType

        binding.qrCodeText.text = tokenVerifyResponse.token

        if (applicationType == ApplicationType.vehicle) {
            setVehicleDetails(tokenVerifyResponse)

        } else if (applicationType == ApplicationType.person) {
            setPersonDetails(tokenVerifyResponse)

        }

        when (status) {
            ApplicationStatus.accepted -> {
                binding.qrCodeStatusLabel.setText(getString(R.string.qr_code_verified_successfully))
                binding.statusIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.activated
                    )
                )
            }
            ApplicationStatus.expired -> {
                binding.qrCodeStatusLabel.setText(getString(R.string.oops_seems_like_an_expired_code))
                binding.statusIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.expired
                    )
                )
            }
            ApplicationStatus.pending -> {
                // todo
            }
            ApplicationStatus.rejected -> {
                binding.qrCodeStatusLabel.setText(getString(R.string.oops_rejected_code))
                binding.statusIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.expired
                    )
                )
            }
        }


        binding.done.setOnClickListener {
            val myIntent = Intent(this, QRCodeScannerActivity::class.java)
            myIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(myIntent)
        }
    }

    private fun setVehicleDetails(tokenVerifyResponse: TokenVerifyResponse) {
        if (CommonUtils.isNotNull(tokenVerifyResponse.entity?.proofId)) {
            val genricKeyValue =
                KeyValueView(this)
            genricKeyValue.setData("Vehicle Number",tokenVerifyResponse.entity?.proofId)
            binding.showLayout.addView(genricKeyValue)
        }

        if (CommonUtils.isNotNull(tokenVerifyResponse.entity?.vehicleModel)) {
            val genricKeyValue =
                KeyValueView(this)
            genricKeyValue.setData("Model",tokenVerifyResponse.entity?.vehicleModel)
            binding.showLayout.addView(genricKeyValue)
        }

        if (CommonUtils.isNotNull(tokenVerifyResponse.entity?.registrationNumber)) {
            val genricKeyValue =
                KeyValueView(this)
            genricKeyValue.setData("Registration Number",tokenVerifyResponse.entity?.registrationNumber)
            binding.showLayout.addView(genricKeyValue)
        }



    }

    private fun setPersonDetails(tokenVerifyResponse: TokenVerifyResponse) {

        if (CommonUtils.isNotNull(tokenVerifyResponse.entity?.firstName)) {
            val fullName = tokenVerifyResponse.entity?.firstName +" "+ tokenVerifyResponse.entity?.lastName
            val genricKeyValue =
                KeyValueView(this)
            genricKeyValue.setData("Name",fullName)
            binding.showLayout.addView(genricKeyValue)

        }

        if (CommonUtils.isNotNull(tokenVerifyResponse.entity?.dob)) {
            val genricKeyValue =
                KeyValueView(this)
            genricKeyValue.setData("D.O.B",tokenVerifyResponse.entity?.dob)
            binding.showLayout.addView(genricKeyValue)

        }

        if (CommonUtils.isNotNull(tokenVerifyResponse.entity?.proofType.toString()) &&
            CommonUtils.isNotNull(tokenVerifyResponse.entity?.proofId.toString())
        ) {
            val genricKeyValue =
                KeyValueView(this)
            genricKeyValue.setData(tokenVerifyResponse.entity?.proofType?.value,tokenVerifyResponse.entity?.proofId)
            binding.showLayout.addView(genricKeyValue)
        }
    }
}