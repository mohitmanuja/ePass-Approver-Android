package com.epass.curfue.models

import androidx.annotation.Keep

@Keep
data class VerifyTokenRequest(
    val token: String,
    val authToken: String
)
