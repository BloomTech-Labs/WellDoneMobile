package com.versilistyson

import android.app.Application
import com.versilistyson.welldone.data.local.SharedPreference

class MyApplication: Application() {

    val appComponent =
        DaggerAppComponent
            .factory()
            .create(this)

    private lateinit var sharedPreferences: SharedPreference

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = SharedPreference(this)
    }

    fun saveToken(token: String){
        sharedPreferences.save(SharedPreference.Keys.USER_TOKEN, token)
    }

    fun retrieveToken(): String? {
        return sharedPreferences.retrieveString(SharedPreference.Keys.USER_TOKEN)
    }
}