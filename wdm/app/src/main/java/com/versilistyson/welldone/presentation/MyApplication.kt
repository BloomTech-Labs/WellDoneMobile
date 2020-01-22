package com.versilistyson.welldone.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import com.versilistyson.welldone.domain.util.Variables
import com.versilistyson.welldone.presentation.di.app.DaggerAppComponent
import com.versilistyson.welldone.presentation.util.SharedPreference

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
        sharedPreferences =
            SharedPreference(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                val connectivityManager =
                    applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network?) {
                        Variables.isNetworkConnected = true // Global Static Variable
                    }

                    override fun onLost(network: Network?) {
                        Variables.isNetworkConnected = false // Global Static Variable
                    }
                }
                )
                Variables.isNetworkConnected = false
            } catch (e: Exception) {
                Variables.isNetworkConnected = false
            }
        }
    }

    fun saveToken(token: String){
        sharedPreferences.save(SharedPreference.Keys.USER_TOKEN, token)
    }

    fun retrieveToken(): String? {
        return sharedPreferences.retrieveString(SharedPreference.Keys.USER_TOKEN)
    }
}