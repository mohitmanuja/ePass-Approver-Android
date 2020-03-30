package com.anumati.approver.utils

object CommonConfig {

    const val SERVER_BASE_URL = "https://viruscorona.co.in"  // Change your base url for server requests
    const val REQUEST_OTP = "/requestOTP"      // To request OTP
    const val VERIFY_OTP = "/verifyOTP"        // To verify OTP
    const val VERIFY_TOKEN = "/verifyToken"    // To Verify Token
    const val OTP_LENGTH = 6                   // Enter OTP Length to verify checks
    const val MANUAL_QR_CODE_LENGTH = 6        // Enter QR Code Length to verify checks

}