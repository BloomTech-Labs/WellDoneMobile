package com.versilistyson.welldone.data.local

import android.app.Application

class SharedPreference(val application: Application) {
    enum class Keys(val keyName: String) {
        USER_TOKEN("USER_TOKEN"),
        USER_ID("USER_ID")
    }
    private val PREFS_NAME = "userStuff"
    val sharedPref = application.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE)

    fun save(KEY_NAME: Keys, value: Int) {
        val editor = sharedPref.edit()
        editor.putInt(KEY_NAME.keyName, value)
        editor.apply()
    }
    fun save(KEY_NAME: Keys, value: String) {
        val editor = sharedPref.edit()
        editor.putString(KEY_NAME.keyName, value)
        editor.apply()
    }
    fun retrieveString(KEY_NAME: Keys): String? {
        return sharedPref.getString(KEY_NAME.keyName, "none")
    }

    fun retreiveInt(KEY_NAME: Keys): Int {
        return sharedPref.getInt(KEY_NAME.keyName, 0)
    }
}