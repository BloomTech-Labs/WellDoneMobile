package com.versilistyson.welldone.domain.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

//for network calls below api level 21 as network callbacks
val Context.networkInfo: NetworkInfo? get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

