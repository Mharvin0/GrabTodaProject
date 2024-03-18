package com.example.todaproj

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todaproj.api.ApiClient
import com.example.todaproj.model.reponse.BookingResponse
import com.example.todaproj.model.reponse.DestinationResponse
import com.example.todaproj.storages.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Booking : AppCompatActivity() {

    private lateinit var spinnerpickUp: Spinner
    private lateinit var spinnerdropOff: Spinner
    private lateinit var buttonCalculateFare: Button
    private lateinit var textViewFare: TextView
    private val apiService = ApiClient.createApiService()
    private lateinit var destinations: List<DestinationResponse>


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
                    val destinations = response.body() ?: emptyList()
                    this@Booking.destinations = destinations
                    setupSpinners(destinations)
                } else {
                    Toast.makeText(applicationContext, "Failed to fetch destinations!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<DestinationResponse>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error fetching destinations: ${t.message}", Toast.LENGTH_SHORT).show()
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
        val pickupLocationId = getSelectedLocationId(spinnerpickUp)
        val dropoffLocationId = getSelectedLocationId(spinnerdropOff)
        if (pickupLocationId == -1 || dropoffLocationId == -1) {
            Toast.makeText(applicationContext, "Please select Pick-up and Drop-off locations!", Toast.LENGTH_SHORT).show()
            return
        }
        val userId = SharedPrefManager.getInstance(applicationContext).getUserId()
        if (userId == -1) {
            Toast.makeText(applicationContext, "User ID is missing!", Toast.LENGTH_SHORT).show()
            return
        }
        saveBooking(userId, pickupLocationId, dropoffLocationId)
    }

    private fun getSelectedLocationId(spinner: Spinner): Int {
        val selectedLocationName = spinner.selectedItem as String
        val destination = destinations?.find { it.location == selectedLocationName }
        return destination?.id ?: -1
    }


    private fun saveBooking(userId: Int, pickupLocation: Int, dropoffLocation: Int) {
        val sharedPrefManager = SharedPrefManager.getInstance(applicationContext)
        val userId = sharedPrefManager.getUserId()
        if (userId == -1) {
            Toast.makeText(applicationContext, "User ID is missing!", Toast.LENGTH_SHORT).show()
            return
        }
        sharedPrefManager.clearBooking()
        sharedPrefManager.saveBooking(userId, pickupLocation, dropoffLocation)
        apiService.createBooking(userId, pickupLocation, dropoffLocation ).enqueue(object : Callback<BookingResponse> {
            override fun onResponse(call: Call<BookingResponse>, response: Response<BookingResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Booked Successfully!", Toast.LENGTH_SHORT).show()
                    goToHistory()
                } else {
                    Toast.makeText(applicationContext, "Booking Failed!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<BookingResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun goToHistory() {
        val intent = Intent(this, History::class.java)
        startActivity(intent)
    }
}
