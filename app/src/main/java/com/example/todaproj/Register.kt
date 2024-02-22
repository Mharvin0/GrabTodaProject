package com.example.todaproj

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Find views
        val fullNameEditText = findViewById<EditText>(R.id.full_name_edit_text)
        val emailEditText = findViewById<EditText>(R.id.email_edit_text)
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirm_password_edit_text)
        val passengerCheckBox = findViewById<CheckBox>(R.id.passenger_checkbox)
        val driverCheckBox = findViewById<CheckBox>(R.id.driver_checkbox)
        val continueButton = findViewById<Button>(R.id.continue_btn)

        // Set click listener for continue button
        continueButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()
            val isPassenger = passengerCheckBox.isChecked
            val isDriver = driverCheckBox.isChecked

            // Perform registration validation
            if (isValidRegistration(fullName, email, password, confirmPassword, isPassenger, isDriver)) {
                // Proceed with registration logic here
                // For demonstration, show a toast message
                showToast("Registration successful")
            } else {
                // Show error message if registration is invalid
                showToast("Invalid registration details")
            }
        }
    }

    // Function to validate registration details
    private fun isValidRegistration(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String,
        isPassenger: Boolean,
        isDriver: Boolean
    ): Boolean {
        // Perform validation according to your requirements
        return fullName.isNotBlank() && email.isNotBlank() &&
                password.isNotBlank() && confirmPassword.isNotBlank() &&
                password == confirmPassword && (isPassenger || isDriver)
    }

    // Function to show toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
