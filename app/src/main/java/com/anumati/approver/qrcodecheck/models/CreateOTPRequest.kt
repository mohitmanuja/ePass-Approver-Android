package com.anumati.approver.qrcodecheck.models

import androidx.annotation.Keep

@Keep
data class CreateOTPRequest(
    val accountIdentifierType: String,
    val identifier: String,
    val publicKey: String
)