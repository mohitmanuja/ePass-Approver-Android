package com.anumati.approver.onBoarding.models

import androidx.annotation.Keep

@Keep
enum class ApplicationStatus {
    pending, accepted, rejected, expired
}

@Keep
enum class ApplicationType {
    person, vehicle
}

@Keep
enum class ProofType(val value:String) {
    AADHAR("aadhar"), DL("dl"), PAN("pan"), ORG("org"), RC("rc"), PASSPORT("passport")
}