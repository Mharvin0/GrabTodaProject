package com.example.todaproj.model.reponse

data class BookingResponse(
    val id: Int,
    val userId: Int,
    val pickupLocation: Int,
    val dropoffLocation: Int
)
