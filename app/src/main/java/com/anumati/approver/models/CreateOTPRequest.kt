package com.anumati.approver.models

import androidx.annotation.Keep

@Keep
data class CreateOTPRequest(
    val accountIdentifierType: String,
    val identifier: String,
    val publicKey: String
)