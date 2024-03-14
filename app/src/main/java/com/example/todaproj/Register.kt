package com.example.todaproj

import android.content.Intent
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todaproj.api.ApiClient
import com.example.todaproj.model.reponse.RegisterResponse
import com.example.todaproj.model.request.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.StringReader

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val editName = findViewById<EditText>(R.id.editTextName)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val editTextCPassword = findViewById<EditText>(R.id.editTextCPassword)

        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        buttonRegister.setOnClickListener {
            val name = editName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val cpassword = editTextCPassword.text.toString().trim()

            val signupDataJson = "{\"name\":\"$name\", \"email\":\"$email\",\"password\":\"$password\" }"

            if (email.isEmpty()){
                editTextEmail.error = "Please enter your Email"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }
            if (name.isEmpty()){
                editName.error = "Please enter your Name"
                editName.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                editTextPassword.error = "Please enter your password"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }
            if (cpassword.isEmpty()){
                editTextCPassword.error = "Please confirm your password"
                editTextCPassword.requestFocus()
                return@setOnClickListener
            }
            if (cpassword != password){
                editTextCPassword.error = "Password does not match"
                editTextCPassword.requestFocus()
                return@setOnClickListener
            }

            try {
                val reader = JsonReader(StringReader (signupDataJson))
                reader.isLenient = true
                reader.beginObject()
                reader.close()
                ApiClient.getInstance(applicationContext).createApiService().createUser(name, email, password).enqueue( object : Callback<RegisterResponse> {
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            Toast.makeText(applicationContext, "Registration successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@Register, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "Registration failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }catch (e: Exception){
                Toast.makeText(applicationContext, "Error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    fun goBack(view: View) {
        onBackPressed()
    }
}
