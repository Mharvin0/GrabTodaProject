package com.example.todaproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class History : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var transactionListView: ListView
    private lateinit var loadMoreButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        titleTextView = findViewById(R.id.title)
        transactionListView = findViewById(R.id.transactionListView)
        loadMoreButton = findViewById(R.id.loadMoreButton)

        titleTextView.text = "History"

        val transaction = mutableListOf<String>()
        for (i in 1..10) {
            transaction.add("Transaction $i")
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, transaction)
        transactionListView.adapter = adapter

        loadMoreButton.setOnClickListener {
            // Load more transaction or perform any desired action
        }
    }
}