package com.epass.curfue.network

import com.epass.curfue.models.VerifyTokenRequest
import com.epass.curfue.models.VerifyTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface RetrofitService {
    @POST("/verifyToken")
    suspend fun postVerifyToken(@Body verifyTokenRequest: VerifyTokenRequest): Response<VerifyTokenResponse>

}