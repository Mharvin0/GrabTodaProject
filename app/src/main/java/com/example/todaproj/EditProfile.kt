package com.example.todaproj

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import android.os.Bundle
import android.widget.Button
import com.example.todaproj.api.ApiClient
import com.example.todaproj.model.reponse.LoginResponse
import com.example.todaproj.service.ApiService
import com.example.todaproj.storages.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfile : AppCompatActivity() {

    private lateinit var newUsernameEditText: EditText
    private lateinit var newPasswordEditText: EditText
    private lateinit var newCPasswordEditText: EditText
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var apiService: ApiService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_edit_profile)

        apiService = ApiClient.createApiService()
        sharedPrefManager = SharedPrefManager.getInstance(this)

        newUsernameEditText = findViewById(R.id.new_username_edit)
        newPasswordEditText = findViewById(R.id.new_password_edit)
        newCPasswordEditText = findViewById(R.id.confirm_password_edit)

        setupConfirmButton()
    }

    private fun setupConfirmButton() {
        val confirmButton = findViewById<Button>(R.id.confirm_button) 
        confirmButton.setOnClickListener {
            val newName = newUsernameEditText.toString()
            val newPassword = newPasswordEditText.toString()
            val newCPassword = newCPasswordEditText.toString()
            val userId = sharedPrefManager.user.id


            if (newName.isNotEmpty()) {
                updateUser(userId, newName, sharedPrefManager.user.password)
            }

            if (newPassword.isNotEmpty() && newCPassword.isNotEmpty()) {
                if (newPassword == newCPassword) {
                    updateUser(userId, sharedPrefManager.user.name, newPassword)
                } else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
        private fun updateUser(userId: Int, name: String,  password: String) {
            apiService.updateUser(userId, name, password).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "Failed to update profile", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "Failed to update profile", Toast.LENGTH_SHORT).show()
                }
            })
        }
}