package com.anumati.approver.utils

object CommonConfig {

    const val SERVER_BASE_URL = "https://viruscorona.co.in"  // Change your base url for server requests
    const val REQUEST_OTP = "https://run.mocky.io/v3/bb9f7364-161f-4f11-9b3e-673068c78aa2"      // To request OTP
    const val VERIFY_OTP = "https://run.mocky.io/v3/7bc3f010-0828-47a6-a2af-97e0594860ea"        // To verify OTP
    const val VERIFY_TOKEN = "https://run.mocky.io/v3/734d45e2-ab47-4985-87e5-190bfdb93d2d"    // To Verify Token
    const val OTP_LENGTH = 6                   // Enter OTP Length to verify checks
    const val MANUAL_QR_CODE_LENGTH = 6        // Enter QR Code Length to verify checks
    const val MAX_ALLOWED_MOBILE_LENGTH = 15   // Enter Max Digits allowed for Mobile Length

}