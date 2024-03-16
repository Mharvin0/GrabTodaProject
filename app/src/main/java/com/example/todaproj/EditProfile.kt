package com.example.todaproj

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import android.os.Bundle
import android.widget.Button

class EditProfile : AppCompatActivity() {

    private lateinit var newUsernameEditText: EditText
    private lateinit var newEmailEditText: EditText
    private lateinit var newPasswordEditText: EditText
    private lateinit var newCPasswordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_edit_profile)

        newUsernameEditText = findViewById(R.id.new_username_edit)
        newEmailEditText = findViewById(R.id.new_email_edit)
        newPasswordEditText = findViewById(R.id.new_password_edit)
        newCPasswordEditText = findViewById(R.id.confirm_password_edit)

        initializeProfileSecrion()
        initializeCardView()
        setupConfirmButton()
    }
    private fun initializeProfileSecrion() {
    }
    private fun initializeCardView() {
    }

    private fun setupConfirmButton() {
        val confirmButton = findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener {
            val newUsername = newUsernameEditText.toString()
            val newEmail = newEmailEditText.toString()
            val newPassword = newPasswordEditText.toString()
            val newCPassword = newCPasswordEditText.toString()

            if (newUsername.isNotEmpty()) {
                changeUsername(newUsername)
            }

            if (newEmail.isNotEmpty()) {
                changeEmail(newEmail)
            }

            if (newPassword.isNotEmpty()) {

            }
            if (newCPassword.isNotEmpty()){
                changePassword(newPassword)
            }
        }
    }

    private fun changeUsername(newUsername: String) {
        Toast.makeText(this, "Username changed to: $newUsername", Toast.LENGTH_SHORT).show()
    }

    private fun changeEmail(newEmail: String) {
        Toast.makeText(this, "Email changed To: $newEmail", Toast.LENGTH_SHORT).show()
    }

    private fun changePassword(newCPassword: String) {
        Toast.makeText(this, "Password changed", Toast.LENGTH_SHORT).show()
    }
}