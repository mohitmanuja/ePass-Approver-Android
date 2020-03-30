package com.anumati.approver.qrCodeCheck.models

import androidx.annotation.Keep

@Keep
data class VerifyTokenRequest(
    val token: String
)
