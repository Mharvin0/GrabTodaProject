package com.example.todaproj

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
                    replaceFragment(Dashboard())
                    true
                }
                R.id.Profile -> {
                    replaceFragment(EditProfile())
                    true
                }
                R.id.Booking -> {
                    replaceFragment(Booking())
                    true
                }
                R.id.History -> {
                    replaceFragment(History())
                    true
                }
                R.id.EditProfile -> {
                    replaceFragment(EditProfile())
                    true
                }
                else -> false
            }
        }
        replaceFragment(Dashboard())

    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}

