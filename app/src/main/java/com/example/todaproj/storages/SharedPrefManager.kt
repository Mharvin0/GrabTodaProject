package com.example.todaproj.storages
import android.content.Context
import com.example.todaproj.model.reponse.User


class SharedPrefManager private constructor(private val context: Context) {

    private val sharedPreferences by lazy { context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE) }

    val isLoggedIn: Boolean
        get() = sharedPreferences.getInt(KEY_ID, -1) != -1

    val user: User
        get() = User(
            sharedPreferences.getInt(KEY_ID, -1),
            sharedPreferences.getString(KEY_EMAIL, null)?: "",
            sharedPreferences.getString(KEY_NAME, null)?: ""
        )

    fun saveUser(user: User) {
        with(sharedPreferences.edit()) {
            putInt(KEY_ID, user.id)
            putString(KEY_EMAIL, user.email)
            putString(KEY_NAME, user.name)
            apply()
        }
    }
    fun clear() {
        sharedPreferences.edit().clear().apply()
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