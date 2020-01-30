package com.versilistyson.welldone.domain.common

import android.content.Context
import android.os.Build
import com.versilistyson.welldone.domain.util.Variables
import com.versilistyson.welldone.domain.util.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {

    val isConnected : Boolean?
        get() =
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            context.networkInfo?.isConnectedOrConnecting
        } else {
            Variables.isNetworkConnected //this is updated via a network callback that is started in application if it's the right api level
        }
}