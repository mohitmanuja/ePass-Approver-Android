package com.anumati.approver.qrcodecheck.models

import androidx.annotation.Keep

@Keep
data class VerifyOTPResponse(
    val authToken: String,
    val publicKey: String
)