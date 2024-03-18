package com.example.todaproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import com.example.todaproj.Home
import com.example.todaproj.Booking
import com.example.todaproj.History
import com.example.todaproj.MyProfile

class Home : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val selectedActivity = when (item.itemId) {
            R.id.Home -> Home::class.java
            R.id.Booking -> Booking::class.java
            R.id.History -> History::class.java
            R.id.Profile -> MyProfile::class.java
            else -> null
        }
        selectedActivity?.let { activityClass ->
            if (!isCurrentActivity(activityClass)) {
                startActivity(Intent(this, activityClass).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                })
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

    private fun isCurrentActivity(activityClass: Class<*>): Boolean {
        return activityClass == this::class.java
    }
}


