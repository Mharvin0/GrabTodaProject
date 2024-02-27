package com.example.todaproj

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Booking : AppCompatActivity() {

    private lateinit var editTextPickup: EditText
    private lateinit var editTextDropoff: EditText
    private lateinit var buttonCalculateFare: Button
    private lateinit var textViewFare: TextView

    // Define a map to store fare rates for specific locations
    private val fareRates = mapOf(
        "Location A" to 10.0,  // Fare for Location A is $10
        "Location B" to 15.0,  // Fare for Location B is $15
        "Location C" to 20.0   // Fare for Location C is $20
        // Add more locations and fares as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        editTextPickup = findViewById(R.id.editTextPickup)
        editTextDropoff = findViewById(R.id.editTextDropoff)
        buttonCalculateFare = findViewById(R.id.buttonCalculateFare)
        textViewFare = findViewById(R.id.textViewFare)

        buttonCalculateFare.setOnClickListener {
            calculateFare()
        }
    }

    private fun calculateFare() {
        val pickupLocation = editTextPickup.text.toString()
        val dropoffLocation = editTextDropoff.text.toString()

        // Check if both pickup and drop-off locations exist in the fare rates map
        if (fareRates.containsKey(pickupLocation) && fareRates.containsKey(dropoffLocation)) {
            val fare = calculateFareFromLocations(pickupLocation, dropoffLocation)
            textViewFare.text = "Fare: $$fare"
        } else {
            textViewFare.text = "Fare calculation not available for these locations"
        }
    }

    private fun calculateFareFromLocations(pickupLocation: String, dropoffLocation: String): Double {
        // Get fare rates for pickup and drop-off locations and calculate total fare
        val pickupFare = fareRates[pickupLocation] ?: 0.0
        val dropoffFare = fareRates[dropoffLocation] ?: 0.0
        return pickupFare + dropoffFare
    }
}
