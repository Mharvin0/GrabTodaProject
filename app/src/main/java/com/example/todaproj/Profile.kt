package com.example.todaproj

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import android.os.Bundle
import android.widget.Button

class Profile : AppCompatActivity() {

    private lateinit var newUsernameEditText: EditText
    private lateinit var newEmailEditText: EditText
    private lateinit var newPasswordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        newUsernameEditText = findViewById(R.id.new_username_edit)
        newEmailEditText = findViewById(R.id.new_email_edit)
        newPasswordEditText = findViewById(R.id.new_password_edit)

        initializeProfileSecrion()
        initializeCardView()
        setupConfirmButton()
    }
    private fun initializeProfileSecrion() {
        // Initialize logic for card view
    }
    private fun initializeCardView() {
        // Initialize logic for card view
    }

    private fun setupConfirmButton() {
        val confirmButton = findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener {
            val newUsername = newUsernameEditText.toString()
            val newEmail = newEmailEditText.toString()
            val newPassword = newPasswordEditText.toString()

            if (newUsername.isNotEmpty()) {
            }

            if (newEmail.isNotEmpty()) {

            }

            if (newPassword.isNotEmpty()) {
                changePassword(newPassword)

            }
        }
    }

    private fun changeUsername(newUsername: String) {
        // Replace this with your logic to change the username
        Toast.makeText(this, "Username changed to: $newUsername", Toast.LENGTH_SHORT).show()
    }

    private fun changeEmail(newEmail: String) {
        //Replace thiis with your logic to change the email
        Toast.makeText(this, "Email changed To: $newEmail", Toast.LENGTH_SHORT).show()
    }

    private fun changePassword(newPassword: String) {
        // Replace this with your logic to change the password
        Toast.makeText(this, "Password changed", Toast.LENGTH_SHORT).show()
    }
}