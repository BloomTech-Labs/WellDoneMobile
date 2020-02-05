package com.versilistyson.welldone.data.util

import androidx.room.ColumnInfo

sealed class StoreKey {
    companion object DefaultKeys {
        const val SENSORS_KEY = "SENSORS_KEY"
        const val LOG_IMAGE_KEY = "LOG_IMAGE_KEY"
        const val USER_DETAILS_KEY = "USER_DETAILS_KEY"
    }
    data class  SensorsKey(
        val key: String = SENSORS_KEY
    )
    data class LogImageKey(
        val key: String = LOG_IMAGE_KEY
    )
}