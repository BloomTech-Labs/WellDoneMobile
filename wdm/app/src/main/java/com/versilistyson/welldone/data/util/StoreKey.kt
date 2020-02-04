package com.versilistyson.welldone.data.util

import androidx.room.ColumnInfo

sealed class StoreKey {
    companion object DefaultKeys {
        const val SENSORS_KEY = "SENSORS_KEY"
        const val USER_DETAILS_KEY = "USER_DETAILS_KEY"
        const val LOG_KEY = "LOG_KEY"
    }
    data class  SensorsKey(
        val key: String = SENSORS_KEY
    )
    data class LogKey(
        val key: String = LOG_KEY
    )
}