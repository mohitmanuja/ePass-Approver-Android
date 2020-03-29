package com.anumati.approver.models

import androidx.annotation.Keep

@Keep
enum class OTPIdentifier(val value: String) {
    PHONE("phone"),EMAIL("email"),AADHAR("aadhar")
}