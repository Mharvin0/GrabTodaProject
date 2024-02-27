package com.example.todaproj

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class Dashboard : AppCompatActivity() {
    var BookingCard: CardView? = null
    var FarecalcCard: CardView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        BookingCard = findViewById(R.id.BookingCard)
        BookingCard!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Dashboard, Booking::class.java)
            startActivity(intent)
        })
    }
}