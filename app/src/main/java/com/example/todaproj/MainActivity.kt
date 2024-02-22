package com.example.todaproj

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views
        val emailEditText = findViewById<EditText>(R.id.email_edit_text)
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
        val loginButton = findViewById<Button>(R.id.login_btn)
        val registerButton = findViewById<Button>(R.id.register_btn)

        // Set click listener for login button
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validate email and password
            if (isValidEmail(email) && isValidPassword(password)) {
                // Perform login operation here
                // For demonstration, show a toast message
                showToast("Login successful")
            } else {
                // Show error message if email or password is invalid
                showToast("Invalid email or password")
            }
        }

        // Set click listener for register button
        registerButton.setOnClickListener {
            // Navigate to the registration activity or perform registration logic
            showToast("Navigate to Register Activity")
        }
    }

    // Function to validate email format
    private fun isValidEmail(email: String): Boolean {
        // Basic email validation
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Function to validate password
    private fun isValidPassword(password: String): Boolean {
        // Basic password validation, you can add more criteria as per your requirement
        return password.length >= 6
    }

    // Function to show toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
