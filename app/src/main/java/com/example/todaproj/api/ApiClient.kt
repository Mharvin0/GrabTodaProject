package com.example.todaproj.api
import android.content.SharedPreferences
import com.example.todaproj.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private lateinit var sharedPreferences : SharedPreferences

    val instance: ApiService by lazy { createInstance() }

    fun setSharedPreferences (pref: SharedPreferences){
        sharedPreferences = pref
    }

    private fun getAuthToken(): String{
        if (!::sharedPreferences.isInitialized){
            throw IllegalStateException("Error")
        }
        val authToken = sharedPreferences.getString("authToken", "")
        return "Bearer $authToken"
    }
    private fun createInstance(): ApiService{
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor{chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", getAuthToken())
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    private const val BASE_URL = "https://grabtoda.online/api/"


}