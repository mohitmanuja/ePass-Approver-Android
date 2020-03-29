package com.anumati.approver.network

import com.anumati.approver.models.TokenVerifyResponse
import com.anumati.approver.models.VerifyTokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface RetrofitService {
    @POST("/verifyToken")
    suspend fun postVerifyToken(@Body verifyTokenRequest: VerifyTokenRequest): Response<TokenVerifyResponse>

}