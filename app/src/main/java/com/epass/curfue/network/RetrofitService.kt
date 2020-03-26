package com.epass.curfue.network

import TokenVerifyResponse
import com.epass.curfue.models.VerifyTokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface RetrofitService {
    @POST("/verifyToken")
    suspend fun postVerifyToken(@Body verifyTokenRequest: VerifyTokenRequest): Response<TokenVerifyResponse>

}