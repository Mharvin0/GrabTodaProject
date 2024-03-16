package com.example.todaproj.model.reponse

data class LoginResponse (
    val error: Boolean,
    val message: String,
    val user: User
)
