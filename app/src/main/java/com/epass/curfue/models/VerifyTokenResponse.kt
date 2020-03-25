package com.epass.curfue.models

import androidx.annotation.Keep

@Keep
data class VerifyTokenResponse(
    val additionalAttributes: AdditionalAttributes,
    val applicationID: Int,
    val fromPlace: FromPlace,
    val fromTime: String,
    val purpose: String,
    val status: String,
    val toPlace: ToPlace,
    val toTime: String,
    val token: String,
    val tripType: String,
    val type: String
){

    @Keep
    data class AdditionalAttributes(
        val issuedToname: String,
        val issuedTonumber: String
    )

    @Keep
    data class FromPlace(
        val city: String,
        val lat: Double,
        val lng: Double,
        val name: String,
        val pincode: String
    )

    @Keep
    data class ToPlace(
        val city: String,
        val lat: Double,
        val lng: Double,
        val name: String,
        val pincode: String
    )
}
