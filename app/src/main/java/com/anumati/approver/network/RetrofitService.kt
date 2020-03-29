package com.anumati.approver.network

import com.anumati.approver.models.*
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