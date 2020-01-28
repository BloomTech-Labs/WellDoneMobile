package com.versilistyson.welldone.data.db

import com.versilistyson.welldone.data.util.RepositoryConstants.Companion.SENSORS_KEY

sealed class StoreKey {

    data class SensorsKey(val key: String = SENSORS_KEY): StoreKey()
}