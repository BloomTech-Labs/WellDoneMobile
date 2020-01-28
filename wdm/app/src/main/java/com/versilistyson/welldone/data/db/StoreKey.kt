package com.versilistyson.welldone.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.versilistyson.welldone.data.util.RepositoryConstants.Companion.SENSORS_KEY

sealed class StoreKey {

    @Entity(tableName = "sensors_key")
    data class SensorsKey(
        @ColumnInfo(name = "key") val key: String = SENSORS_KEY,
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long = 0): StoreKey()

}