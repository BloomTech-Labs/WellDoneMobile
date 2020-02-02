package com.versilistyson.welldone.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import com.versilistyson.welldone.domain.util.Variables
import com.versilistyson.welldone.presentation.di.app.component.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class MyApplication: Application() {

    val appComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}