package com.versilistyson.welldone.data.util

sealed class SharedPreferenceKeys(val KEY: String) {

    companion object PreferenceFileKeys {
        private const val AUTHENTICATION_KEY = "com.versilistyson.welldone.AUTHENTICATION"
    }
    object Authentication : SharedPreferenceKeys(AUTHENTICATION_KEY) {
        val USER_ID = "com.versilistyson.welldone.USER_ID"
        val USER_TOKEN = "com.versilistyson.welldone.USER_TOKEN"
    }
}



