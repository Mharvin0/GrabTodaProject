package com.example.todaproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent

class Home : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.Home -> {
                startActivity(Intent(this, Home::class.java))
                return@OnNavigationItemSelectedListener true
            }
            R.id.Booking -> {
                startActivity(Intent(this, Booking::class.java))
                return@OnNavigationItemSelectedListener true
            }
            R.id.History -> {
                startActivity(Intent(this, History::class.java))
                return@OnNavigationItemSelectedListener true
            }
            R.id.Profile-> {
                startActivity(Intent(this, MyProfile::class.java))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}


