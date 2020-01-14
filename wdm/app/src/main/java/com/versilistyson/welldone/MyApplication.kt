package com.versilistyson.welldone

import android.app.Application
import com.versilistyson.welldone.data.local.SharedPreference
import com.versilistyson.welldone.di.DaggerAppComponent

class MyApplication: Application() {

    val appComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

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