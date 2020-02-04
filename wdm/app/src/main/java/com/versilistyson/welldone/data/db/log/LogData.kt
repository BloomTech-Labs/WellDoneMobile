package com.versilistyson.welldone.data.db.log

import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.data.util.Mappable
import com.versilistyson.welldone.domain.framework.entity.Entity

@androidx.room.Entity(
    tableName = "log_table",
    foreignKeys = [ForeignKey(
        entity = SensorData::class,
        parentColumns = ["sensor_id"],
        childColumns = ["sensor_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["log_id"], unique = true),
                Index(value = ["sensor_id"], unique = false)]
)
data class LogData(
    @ColumnInfo(name = "log_id") @PrimaryKey(autoGenerate = false) val logId: Long,
    @ColumnInfo(name = "date_filed") val dateFiled: String,
    @ColumnInfo(name = "last_modified") val lastModified: String,
    @ColumnInfo(name = "status") val status: Int?,
    @ColumnInfo(name = "comment") val comment: String,
    @ColumnInfo(name = "operator_id") val operatorId: Long,
    @ColumnInfo(name = "sensor_id") val sensorId: Long,
    @ColumnInfo(name = "org_name") val organizationName: String
): Mappable<Entity.LogDetails> {
    override fun map() =
        Entity.LogDetails(
            logId = logId,
            sensorId = sensorId,
            dateFiled = dateFiled,
            lastModified = lastModified,
            status = status,
            comment = comment,
            operatorId = operatorId,
            logImages = null //this will be added after we retrieve the images from store
        )
}