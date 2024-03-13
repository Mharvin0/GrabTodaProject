package com.example.todaproj.service

import com.example.todaproj.model.reponse.LoginResponse
import com.example.todaproj.model.reponse.RegisterResponse
import com.example.todaproj.model.request.LoginRequest
import com.example.todaproj.model.request.RegisterRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("store")
    fun createUser(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String,
    ):retrofit2.Call<RegisterResponse>
}