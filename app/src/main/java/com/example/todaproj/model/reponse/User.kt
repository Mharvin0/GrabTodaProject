package com.example.todaproj.model.reponse

import retrofit2.Call

data class User(
    val id: Int,
    val email: String,
    val name: String,
    val password: String
)
