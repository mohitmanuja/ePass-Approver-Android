package com.anumati.approver.network

import com.anumati.approver.onBoarding.models.TokenVerifyResponse
import com.anumati.approver.qrcodecheck.models.CreateOTPRequest
import com.anumati.approver.qrcodecheck.models.VerifyOTPRequest
import com.anumati.approver.qrcodecheck.models.VerifyOTPResponse
import com.anumati.approver.qrcodecheck.models.VerifyTokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface RetrofitService {
    @POST("/requestOTP")
    suspend fun postRequestOTP(@Body createOTPRequest: CreateOTPRequest): Response<Void>

    @POST("/verifyOTP")
    suspend fun postVerifyOTP(@Body verifyOTPRequest: VerifyOTPRequest): Response<VerifyOTPResponse>

    @POST("/verifyToken")
    suspend fun postVerifyToken(@Body verifyTokenRequest: VerifyTokenRequest): Response<TokenVerifyResponse>

}