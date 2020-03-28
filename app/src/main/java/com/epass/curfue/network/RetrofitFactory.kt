package com.epass.curfue.network

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
//import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    const val BASE_URL = "https://demo4807437.mockable.io"
//    const val BASE_URL = "https://epass.egovernments.org:8091"
    var client: OkHttpClient? = null



    fun makeRetrofitService(context: Context): RetrofitService {
        if (client == null){
            client =  OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
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