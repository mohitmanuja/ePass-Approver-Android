package com.anumati.approver.onBoarding.models

import androidx.annotation.Keep

@Keep
enum class OTPIdentifier(val value: String) {
    PHONE("phone"),EMAIL("email"),AADHAR("aadhar")
}