package com.anumati.approver.qrcodecheck.models

import androidx.annotation.Keep

@Keep
data class VerifyTokenRequest(
    val token: String
)
