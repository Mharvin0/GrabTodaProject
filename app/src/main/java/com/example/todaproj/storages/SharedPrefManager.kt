package com.example.todaproj.storages
import android.content.Context
import com.example.todaproj.model.reponse.User


class SharedPrefManager private constructor(private val context: Context) {

    private val sharedPreferences by lazy { context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE) }
    private val KEY_PASSWORD = "password"
    private val KEY_PICKUP_LOCATION = "pickup_location"
    private val KEY_DROPOFF_LOCATION = "dropoff_location"
    private val BOOKING_HISTORY_KEY = "booking_history"


    val isLoggedIn: Boolean
        get() {
            val loggedInUserId = sharedPreferences.getInt(KEY_ID, -1)
            return loggedInUserId != -1
        }

    val user: User
        get() = User(
            sharedPreferences.getInt(KEY_ID, -1),
            sharedPreferences.getString(KEY_EMAIL, null)?: "",
            sharedPreferences.getString(KEY_NAME, null)?: "",
            sharedPreferences.getString(KEY_PASSWORD, null)?: ""
        )

    fun saveUser(user: User) {
        with(sharedPreferences.edit()) {
            putInt(KEY_ID, user.id)
            putString(KEY_EMAIL, user.email)
            putString(KEY_NAME, user.name)
            apply()
        }
    }
    fun getUserId(): Int {
        return sharedPreferences.getInt(KEY_ID, -1)
    }
    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    fun clearBooking(){
        sharedPreferences.edit().remove(KEY_PICKUP_LOCATION).remove(KEY_DROPOFF_LOCATION).apply()
    }

    fun saveBooking(userId: Int, pickupLocation: Int, dropoffLocation: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_ID, userId)
        editor.putInt(KEY_PICKUP_LOCATION, pickupLocation)
        editor.putInt(KEY_DROPOFF_LOCATION, dropoffLocation)
        editor.apply()
    }

    fun getBookingHistory(): List<String> {
        return sharedPreferences.getStringSet(BOOKING_HISTORY_KEY, emptySet())?.toList() ?: emptyList()
    }

    companion object {
        private const val SHARED_PREF_NAME = "my_shared_pref"
        private const val KEY_ID = "user_id"
        private const val KEY_EMAIL = "email"
        private const val KEY_NAME = "name"

        @Volatile
        private var INSTANCE: SharedPrefManager? = null

        fun getInstance(context: Context): SharedPrefManager {
            return INSTANCE ?: synchronized(this) {
                val instance = SharedPrefManager(context)
                INSTANCE = instance
                instance
            }
        }
    }
}