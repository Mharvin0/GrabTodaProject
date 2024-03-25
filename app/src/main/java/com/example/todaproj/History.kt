package com.example.todaproj

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.todaproj.storages.SharedPrefManager

class History : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var transactionListView: ListView
    private lateinit var loadMoreButton: Button
    private lateinit var pickupLocationTextView: TextView
    private lateinit var dropoffLocationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        titleTextView = findViewById(R.id.title)
        transactionListView = findViewById(R.id.transactionListView)
        loadMoreButton = findViewById(R.id.loadMoreButton)
        pickupLocationTextView = findViewById(R.id.pickUp)
        dropoffLocationTextView = findViewById(R.id.dropOff)

        val bookingHistory = SharedPrefManager.getInstance(this).getBookingHistory()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bookingHistory)
        transactionListView.adapter = adapter

        transactionListView.setOnItemClickListener { _, _, position, _ ->
            val bookingDetails = bookingHistory[position].split(", ")
            val pickupLocation = bookingDetails[0].substringAfter(": ")
            val dropoffLocation = bookingDetails[1].substringAfter(": ")
            pickupLocationTextView.text = "Pickup Location: $pickupLocation"
            dropoffLocationTextView.text = "Dropoff Location: $dropoffLocation"
            showBookingDetails()
        }

        loadMoreButton.setOnClickListener {
        }
    }

    private fun showBookingDetails() {
        pickupLocationTextView.visibility = TextView.VISIBLE
        dropoffLocationTextView.visibility = TextView.VISIBLE
    }
}
