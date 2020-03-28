package com.epass.curfue.repos

import android.content.Context
import com.epass.curfue.models.TokenVerifyResponse
import com.epass.curfue.models.VerifyTokenRequest
import com.epass.curfue.network.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class TokenRepo(private val context: Context) {

    fun fetchTokenResult(
        token: String,
        service: RetrofitService,
        listener: ((Response<TokenVerifyResponse>) -> Unit)?
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            val verifyTokenRequest = VerifyTokenRequest(token)
            val response = service.postVerifyToken(verifyTokenRequest)
            listener?.invoke(response)

        }
    }
}