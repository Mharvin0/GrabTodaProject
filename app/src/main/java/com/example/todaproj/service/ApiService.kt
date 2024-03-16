package com.example.todaproj.service

import com.example.todaproj.model.reponse.LoginResponse
import com.example.todaproj.model.reponse.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    @Headers("Accept: application/json")
    fun createUser(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String,
    ):Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    @Headers("Accept: application/json")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password: String
    ):Call<LoginResponse>
}