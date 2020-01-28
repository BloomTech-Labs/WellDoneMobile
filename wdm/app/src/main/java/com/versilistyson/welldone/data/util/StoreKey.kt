package com.versilistyson.welldone.data.util

import androidx.room.ColumnInfo

sealed class StoreKey {
    companion object DefaultKeys {
        const val SENSORS_KEY = "SENSORS_KEY"
        const val USER_DETAILS_KEY = "USER_DETAILS_KEY"
    }
    data class  SensorsKey(
        @ColumnInfo(name = "key") val key: String = SENSORS_KEY
    )
}