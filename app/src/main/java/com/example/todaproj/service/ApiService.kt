package com.example.todaproj.service

import com.example.todaproj.model.reponse.BookingResponse
import com.example.todaproj.model.reponse.DestinationResponse
import com.example.todaproj.model.reponse.LoginResponse
import com.example.todaproj.model.reponse.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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

    @FormUrlEncoded
    @PUT("update-profile/{id}")
    @Headers("Accept: application/json")
    fun updateUser(
        @Path("id") id: Int,
        @Field("name") name:String,
        @Field("password") password: String,
    ):Call<LoginResponse>

    @GET("destination")
    @Headers("Accept: application/json")
    fun getDestinations(): Call<List<DestinationResponse>>

    @FormUrlEncoded
    @POST("bookings")
    @Headers("Accept: application/json")
    fun createBooking(
        @Field("user_id") userId: Int,
        @Field("pickup") pickupLocation: Int,
        @Field("dropoff") dropoffLocation: Int
    ): Call<BookingResponse>


}