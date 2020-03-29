package com.anumati.approver.network

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
//import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    const val BASE_URL = "https://viruscorona.co.in"
    var client: OkHttpClient? = null

    fun makeRetrofitService(context: Context): RetrofitService {
        if (client == null){
            client =  OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(ChuckInterceptor(context))
                .build()
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build().create(RetrofitService::class.java)
    }
}