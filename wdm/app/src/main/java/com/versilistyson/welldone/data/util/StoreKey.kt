package com.versilistyson.welldone.data.util

sealed class StoreKey {
    companion object DefaultKeys {
        const val SENSORS_KEY = "SENSORS_KEY"
        const val USER_DETAILS_KEY = "USER_DETAILS_KEY"
    }
    data class  SensorsKey(
        val key: String = SENSORS_KEY
    )
    data class LogImageKey(
        val id: Long
    )
    data class LogKey(
        val id: Long
    )
}