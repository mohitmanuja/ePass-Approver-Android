package com.anumati.approver.models

import androidx.annotation.Keep

@Keep
data class VerifyTokenRequest(
    val token: String?
)
