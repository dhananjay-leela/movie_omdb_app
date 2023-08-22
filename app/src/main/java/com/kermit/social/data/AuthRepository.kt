package com.kermit.social.data

import android.content.SharedPreferences

class AuthRepository(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val PREF_KEY_IS_AUTHENTICATED = "is_authenticated"
    }

    private val users = mutableMapOf<String, String>()

    fun register(username: String, password: String): Boolean {
        if (users.containsKey(username)) {
            return false
        }
        users[username] = password
        return true
    }

    fun signIn(username: String, password: String): Boolean {
        val auth = users[username] == password
        if (auth) {
            sharedPreferences.edit().putBoolean(PREF_KEY_IS_AUTHENTICATED, true).apply()
        }
        return auth
    }

    fun signOut() {
        sharedPreferences.edit().putBoolean(PREF_KEY_IS_AUTHENTICATED, false).apply()
    }

    fun isUserAuthenticated(): Boolean {
        return sharedPreferences.getBoolean(PREF_KEY_IS_AUTHENTICATED, false)
    }
}