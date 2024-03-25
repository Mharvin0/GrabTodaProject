package com.example.todaproj

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val bookingCard = findViewById<View>(R.id.bookingCard)
        val historyCard = findViewById<View>(R.id.historyCard)
        val profileCard = findViewById<View>(R.id.profileCard)
        val logoutCard = findViewById<View>(R.id.logoutCard)

        bookingCard.setOnClickListener {
            val intent = Intent(this, Booking::class.java)
            startActivity(intent)
        }

        historyCard.setOnClickListener {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }

        profileCard.setOnClickListener {
            val intent = Intent(this, MyProfile::class.java)
            startActivity(intent)
        }

        logoutCard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}
