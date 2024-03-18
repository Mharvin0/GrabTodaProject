package com.example.todaproj

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.todaproj.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Spinner
import com.example.todaproj.model.reponse.DestinationResponse


class Booking : AppCompatActivity() {

    private lateinit var spinnerpickUp: Spinner
    private lateinit var spinnerdropOff: Spinner
    private lateinit var buttonCalculateFare: Button
    private lateinit var textViewFare: TextView
    private val apiService = ApiClient.createApiService()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        spinnerpickUp = findViewById(R.id.pickUp)
        spinnerdropOff = findViewById(R.id.dropOff)
        buttonCalculateFare = findViewById(R.id.buttonCalculateFare)
        textViewFare = findViewById(R.id.textViewFare)

        buttonCalculateFare.setOnClickListener {
            bookRide()
        }
        fetchDestinations()
    }

    private fun fetchDestinations() {
        apiService.getDestinations().enqueue(object : Callback<List<DestinationResponse>> {

            override fun onResponse(
                call: Call<List<DestinationResponse>>,
                response: Response<List<DestinationResponse>>
            ) {
                if (response.isSuccessful) {
                    val destinations = response.body()
                    destinations?.let {
                        setupSpinners(it)
                    }
                } else {

                }
            }
            override fun onFailure(call: Call<List<DestinationResponse>>, t: Throwable) {
            }
        })
    }
    private fun setupSpinners(destinations: List<DestinationResponse>) {
        val locationList = destinations.map { it.location }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerpickUp.adapter = adapter
        spinnerdropOff.adapter = adapter
    }

    private fun bookRide() {
        val pickupLocation = spinnerpickUp.selectedItem.toString()
        val dropoffLocation = spinnerdropOff.selectedItem.toString()
        saveBooking(pickupLocation, dropoffLocation)
    }
    private fun saveBooking(pickupLocation: String, dropoffLocation: String) {
        val sharedPreferences = getSharedPreferences("bookings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val bookingKey = "Booking_${System.currentTimeMillis()}"
        editor.putString(bookingKey, "Pickup: $pickupLocation, Drop-off: $dropoffLocation")
        editor.apply()
    }
}
