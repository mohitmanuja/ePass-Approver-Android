package com.anumati.approver.network

import android.content.Context
import com.anumati.approver.utils.CommonConfig.SERVER_BASE_URL
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    var client: OkHttpClient? = null

    fun makeRetrofitService(context: Context): RetrofitService {
        if (client == null){
            client =  OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(ChuckInterceptor(context)) // To intercept chuck
                .build()
        }

        return Retrofit.Builder()
            .baseUrl(SERVER_BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build().create(RetrofitService::class.java)
    }
}