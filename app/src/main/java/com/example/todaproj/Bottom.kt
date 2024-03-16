package com.example.todaproj

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Bottom : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.dashboard -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.Profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                R.id.Booking -> {
                    replaceFragment(BookingFragment())
                    true
                }
                R.id.History -> {
                    replaceFragment(HistoryFragment())
                    true
                }
                R.id.EditProfile -> {
                    replaceFragment(EditProfileFragment())
                    true
                }
                else -> false
            }
        }
        replaceFragment(HomeFragment())

    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}

