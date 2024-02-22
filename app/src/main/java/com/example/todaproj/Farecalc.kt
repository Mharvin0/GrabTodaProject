package com.example.todaproj

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Farecalc : AppCompatActivity() {

    private lateinit var editTextDistance: EditText
    private lateinit var editTextFareRate: EditText
    private lateinit var buttonCalculateFare: Button
    private lateinit var textViewFareResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farecalc)

        editTextDistance = findViewById(R.id.editTextDistance)
        editTextFareRate = findViewById(R.id.editTextFareRate)
        buttonCalculateFare = findViewById(R.id.buttonCalculateFare)
        textViewFareResult = findViewById(R.id.textViewFareResult)

        buttonCalculateFare.setOnClickListener {
            calculateFare()
        }
    }

    private fun calculateFare() {
        val distanceStr = editTextDistance.text.toString().trim()
        val fareRateStr = editTextFareRate.text.toString().trim()

        if (distanceStr.isEmpty() || fareRateStr.isEmpty()) {
            textViewFareResult.text = "Please enter both distance and fare rate."
            return
        }

        val distance = distanceStr.toDouble()
        val fareRate = fareRateStr.toDouble()

        val fare = distance * fareRate

        textViewFareResult.text = String.format("Your fare is ₱%.2f", fare)
    }
}
