package com.versilistyson.welldone.data.db.sensor

import androidx.room.ColumnInfo
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.versilistyson.welldone.data.util.Mappable
import com.versilistyson.welldone.domain.framework.entity.Entity

@androidx.room.Entity(
    tableName = "sensor_table"
)
class SensorData(
    @ColumnInfo(name = "sensor_id") @PrimaryKey(autoGenerate = false) val sensorId: Int,
    @ColumnInfo(name = "status") val sensorStatus: Int?,
    @ColumnInfo(name = "last_upload_date") val lastUploadDate: String,
    @ColumnInfo(name = "district_name") val districtName: String,
    @ColumnInfo(name = "commune") val commune: String,
    @ColumnInfo(name = "province") val province: String,
    @ColumnInfo(name = "village") val village: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "depth") val wellDepth: Double,
    @ColumnInfo(name = "pad_count_0") val padCount0: Double?,
    @ColumnInfo(name = "pad_count_1") val padCount1: Double?,
    @ColumnInfo(name = "pad_count_2") val padCount2: Double?,
    @ColumnInfo(name = "pad_count_3") val padCount3: Double?,
    @ColumnInfo(name = "pad_seconds_0") val padSeconds0: Double?,
    @ColumnInfo(name = "pad_seconds_1") val padSeconds1: Double?,
    @ColumnInfo(name = "pad_seconds_2") val padSeconds2: Double?,
    @ColumnInfo(name = "pad_seconds_3") val padSeconds3: Double?
): Mappable<Entity.Sensor> {

    override fun map(): Entity.Sensor =
       Entity.Sensor(
           sensorId = sensorId,
           sensorStatus = sensorStatus,
           lastUploadDate = lastUploadDate,
           districtName = districtName,
           commune = commune,
           province = province,
           village = village,
           wellDepth = wellDepth,
           location = LatLng(latitude, longitude),
           padCounts = Entity.Sensor.PadCounts(padCount0, padCount1, padCount2, padCount3),
           padSeconds = Entity.Sensor.PadSeconds(padSeconds0, padSeconds1, padSeconds2, padSeconds3)
       )
}