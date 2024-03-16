package com.example.todaproj

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todaproj.api.ApiClient
import com.example.todaproj.model.reponse.LoginResponse
import com.example.todaproj.storages.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailEditText = findViewById<EditText>(R.id.email_edit_text)
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
        val loginButton = findViewById<Button>(R.id.login_btn)
        val registerButton = findViewById<Button>(R.id.register_btn)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty()){
                emailEditText.error = "Please enter your Email"
                emailEditText.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                passwordEditText.error = "Please enter your password"
                passwordEditText.requestFocus()
                return@setOnClickListener
            }

            ApiClient.getInstance(applicationContext).createApiService().userLogin(email,password)
                .enqueue(object: Callback<LoginResponse>{
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.e("LoginFailure", "Failed to login: ${t.message}")
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful && response.body() != null){
                            val intent = Intent(applicationContext, Dashboard::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)

                        }else{
                            val errorMessage = response.errorBody()?.string()?: "Unknown error"
                            Log.e("LoginError", "Login failed: $errorMessage")
                            Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    }
                })
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}
