package com.example.todaproj.service

import com.example.todaproj.model.reponse.LoginResponse
import com.example.todaproj.model.reponse.RegisterResponse
import com.example.todaproj.model.request.LoginRequest
import com.example.todaproj.model.request.RegisterRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("api/users")
    suspend fun getPost(): Response<POST>

    @POST("/authenticate")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/api/users")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
}