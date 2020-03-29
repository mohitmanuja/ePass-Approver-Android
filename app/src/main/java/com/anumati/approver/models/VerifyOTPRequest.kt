package com.anumati.approver.models

import androidx.annotation.Keep

@Keep
data class VerifyOTPRequest(
    val accountIdentifierType: String,
    val identifier: String,
    val otp: String
)