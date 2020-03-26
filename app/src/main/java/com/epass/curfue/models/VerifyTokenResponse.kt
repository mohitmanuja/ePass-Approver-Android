package com.epass.curfue.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class VerifyTokenResponse(
    val additionalAttributes: AdditionalAttributes?,
    val age: Int?,
    val applicationID: Long,
    val fromPlace: Place?,
    val fromTime: String?,
    val aadharID: String?,
    val purpose: String?,
    val status: TokenStatus,
    val toPlace: Place?,
    val reason: String?,
    val toTime: String?,
    val token: String,
    val tripType: String?,
    val error: String?,
    val message: String?,
    val type: String?
) : Parcelable {

    @Keep
    @Parcelize
    data class AdditionalAttributes(
        val issuedToname: String?,
        val issuedTonumber: String?
    ) : Parcelable

    @Keep
    @Parcelize
    data class Place(
        val city: String?,
        val lat: Double?,
        val lng: Double?,
        val name: String?,
        val pincode: String?
    ) : Parcelable


}
