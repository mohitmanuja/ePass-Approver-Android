package com.anumati.approver.qrCodeCheck.models

import androidx.annotation.Keep

@Keep
data class VerifyOTPResponse(
    val authToken: String,
    val publicKey: String
)