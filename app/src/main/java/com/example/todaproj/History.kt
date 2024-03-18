package com.example.todaproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.todaproj.storages.SharedPrefManager

class History : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var transactionListView: ListView
    private lateinit var loadMoreButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        titleTextView = findViewById(R.id.title)
        titleTextView.text = "Booking History"
        transactionListView = findViewById(R.id.transactionListView)
        loadMoreButton = findViewById(R.id.loadMoreButton)

        val bookingHistory = SharedPrefManager.getInstance(this).getBookingHistory()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bookingHistory)

        transactionListView.adapter = adapter

        loadMoreButton.setOnClickListener {
        }
    }
}