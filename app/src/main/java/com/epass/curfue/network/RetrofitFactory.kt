package com.epass.curfue.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    const val BASE_URL = "https://mobileinfo.p.rapidapi.com/"
    var client: OkHttpClient? = null



/*    fun makeRetrofitService(context: Context): RetrofitService {
        if (client == null){
            client = OkHttpClient().newBuilder().connectTimeout(20, TimeUnit.SECONDS)
                .callTimeout(2, TimeUnit.MINUTES)
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
    }*/
}