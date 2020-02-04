package com.versilistyson.welldone.data.db.sensor

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.versilistyson.welldone.data.util.Mappable
import com.versilistyson.welldone.domain.framework.entity.Entity

@androidx.room.Entity(
    tableName = "sensor_table"
)
data class SensorData(
    @ColumnInfo(name = "sensor_id") @PrimaryKey(autoGenerate = false) val sensorId: Int,
    @ColumnInfo(name = "status") val sensorStatus: Int?,
    @ColumnInfo(name = "last_upload_date") val lastUploadDate: String,
    @ColumnInfo(name = "district_name") val districtName: String,
    @ColumnInfo(name = "commune") val commune: String,
    @ColumnInfo(name = "province") val province: String,
    @ColumnInfo(name = "village_name") val villageName: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "depth") val wellDepth: Double
): Mappable<Entity.Sensor> {

    override fun map(): Entity.Sensor =
       Entity.Sensor(
           sensorId = sensorId,
           sensorStatus = sensorStatus,
           lastUploadDate = lastUploadDate,
           districtName = districtName,
           commune = commune,
           province = province,
           village = villageName,
           wellDepth = wellDepth,
           location = LatLng(latitude, longitude)
       )
}