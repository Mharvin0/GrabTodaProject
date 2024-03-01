package com.example.todaproj

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todaproj.client.ApiClient
import com.example.todaproj.model.reponse.RegisterResponse
import com.example.todaproj.model.request.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        buttonRegister.setOnClickListener {
            getInputs()
        }
    }
    private fun getInputs(){
        val editName = findViewById<EditText>(R.id.editTextName)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val editTextCPassword = findViewById<EditText>(R.id.editTextCPassword)
        val name = editName.text.toString()
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        val cpassword = editTextCPassword.text.toString()
        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {

            if (password == cpassword) {
                registerUser(name, email, password)
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()}
        }else {
            Toast.makeText(this, "Please fill in the required credentials!", Toast.LENGTH_SHORT).show()}
    }


    private fun registerUser(name: String, email: String, password: String){
        val registerRequest = RegisterRequest(name, email, password);

        val apiCall = ApiClient.getApiService().registerUser(registerRequest)
        apiCall.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {

                if (response.isSuccessful){
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }else{

                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {

            }

        })
    }
}
