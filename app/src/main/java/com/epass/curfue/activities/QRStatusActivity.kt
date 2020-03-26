package com.epass.curfue.activities

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.epass.curfue.R
import com.epass.curfue.databinding.ActivityQrStatusBinding
import com.epass.curfue.models.ApplicationStatus
import com.epass.curfue.models.ApplicationType
import com.epass.curfue.models.TokenVerifyResponse
import com.epass.curfue.utils.CommonUtils
import com.epass.curfue.utils.gone
import com.epass.curfue.utils.visible


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
            binding.row1.visible()
            binding.row1Label.text = "Vehicle Number"
            binding.row1Text.text = tokenVerifyResponse.entity?.proofId
        }

        if (CommonUtils.isNotNull(tokenVerifyResponse.entity?.vehicleModel)) {
            binding.row2.visible()
            binding.row2Label.text = "Model"
            binding.row2Text.text = tokenVerifyResponse.entity?.vehicleModel
        }

        if (CommonUtils.isNotNull(tokenVerifyResponse.entity?.registrationNumber)) {
            binding.row3.visible()
            binding.row3Label.text = "Registration Number"
            binding.row3Text.text = tokenVerifyResponse.entity?.registrationNumber
        }


    }

    private fun setPersonDetails(tokenVerifyResponse: TokenVerifyResponse) {

        if (CommonUtils.isNotNull(tokenVerifyResponse.entity?.firstName)) {
            binding.row1.visible()
            binding.row1Label.text = "Name"
            val fullName =
                tokenVerifyResponse.entity?.firstName + tokenVerifyResponse.entity?.lastName
            binding.row1Text.text = fullName

        }

        if (CommonUtils.isNotNull(tokenVerifyResponse.entity?.dob)) {
            binding.row2.visible()
            binding.row2Label.text = "D.O.B"
            binding.row2Text.text = tokenVerifyResponse.entity?.dob

        }

        if (CommonUtils.isNotNull(tokenVerifyResponse.entity?.proofType.toString()) &&
            CommonUtils.isNotNull(tokenVerifyResponse.entity?.proofId.toString())
        ) {

            binding.row3.visible()
            binding.row3Label.text = tokenVerifyResponse.entity?.proofType?.value
            binding.row3Text.text = tokenVerifyResponse.entity?.proofId

        }
    }
}