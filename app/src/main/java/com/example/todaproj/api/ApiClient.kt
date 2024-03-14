package com.example.todaproj.api

import android.content.Context
import android.content.SharedPreferences
import com.example.todaproj.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Base64
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val BASE_URL = "https://grabtoda.online/api/"
    private var mInstance: ApiClient? = null
    private lateinit var retrofit: Retrofit

    fun getInstance(context: Context): ApiClient {
        if (mInstance == null) {
            synchronized(ApiClient::class.java) {
                if (mInstance == null) { mInstance = ApiClient }
            }
        }
        return mInstance!!
    }

    fun createApiService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
