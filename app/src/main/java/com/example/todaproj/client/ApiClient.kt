package com.example.todaproj.client
import com.example.todaproj.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object ApiClient {

    private const val BASE_URL = "https://grabtoda.online/api/users/"

    fun getRetrofit(): Retrofit{

        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit;
    }

    fun getApiService():ApiService{
        return getRetrofit().create(ApiService::class.java)
    }
}