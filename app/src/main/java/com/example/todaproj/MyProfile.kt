package com.example.todaproj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.todaproj.storages.SharedPrefManager

class MyProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val emailTextView = findViewById<TextView>(R.id.emailTextView)

        val sharedPrefManager = SharedPrefManager.getInstance(this)
        val user = sharedPrefManager.user

        if (sharedPrefManager.isLoggedIn) {
            val user = sharedPrefManager.user
            nameTextView.text = user.name
            emailTextView.text = user.email

        } else {
        }

        val editProfileButton = findViewById<Button>(R.id.editProfileButton)
        editProfileButton.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }
    }
}


