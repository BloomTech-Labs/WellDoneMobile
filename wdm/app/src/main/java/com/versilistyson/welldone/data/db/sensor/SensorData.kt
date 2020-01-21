package com.versilistyson.welldone.data.db.sensor

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.*

@Entity(
    tableName = "sensor_table",
    indices = [Index(value = ["remote_id"], unique = true)]
)
data class SensorData(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "remote_id") val remoteId: Long,
    @ColumnInfo(name = "last_network_update") val lastNetworkUpdate: Date,
    @ColumnInfo(name = "status") val sensorStatus: Int?,
    @ColumnInfo(name = "last_upload_date") val lastUploadDate: Int?,
    @ColumnInfo(name = "district_name") val districtName: String,
    @ColumnInfo(name = "commune") val commune: String,
    @ColumnInfo(name = "province") val province: String,
    @ColumnInfo(name = "village") val village: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "depth") val wellDepth: Int,
    @ColumnInfo(name = "pad_count_0") val padCount0: Double?,
    @ColumnInfo(name = "pad_count_1") val padCount1: Double?,
    @ColumnInfo(name = "pad_count_2") val padCount2: Double?,
    @ColumnInfo(name = "pad_count_3") val padCount3: Double?,
    @ColumnInfo(name = "pad_seconds_0") val padSeconds0: Double?,
    @ColumnInfo(name = "pad_seconds_1") val padSeconds1: Double?,
    @ColumnInfo(name = "pad_seconds_2") val padSeconds2: Double?,
    @ColumnInfo(name = "pad_seconds_3") val padSeconds3: Double?
)