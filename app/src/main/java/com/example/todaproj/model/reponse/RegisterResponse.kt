package com.example.todaproj.model.reponse

import com.google.gson.annotations.SerializedName
import java.lang.Error

data class RegisterResponse (
    @SerializedName("error") val error: Boolean,
    @SerializedName("message") val message: String
)