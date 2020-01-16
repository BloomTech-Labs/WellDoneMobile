package com.versilistyson.welldone.data.db.sensor

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class SensorData {
    @Entity(tableName = "sensor_table")
    data class Sensor(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val id: Int,
        @ColumnInfo(name = "org_id") val orgId: Int
    )
}