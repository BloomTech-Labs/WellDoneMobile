package com.versilistyson.welldone.data.db.log

import android.graphics.drawable.Drawable
import androidx.room.*
import com.mapbox.mapboxsdk.style.expressions.Expression.array
import com.versilistyson.welldone.data.db.sensor.SensorData

@Entity(
    tableName = "log_table",
    foreignKeys = [ForeignKey(
        entity = SensorData::class,
        parentColumns = ["remote_id"],
        childColumns = ["remote_sensor_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["remote_id"], unique = true)]
)
data class LogData(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "remote_id") val remoteId: Long,
    @ColumnInfo(name = "remote_sensor_id") val remoteSensorId: Long,
    @ColumnInfo(name = "date_filed") val dateFiled: String,
    @ColumnInfo(name = "last_modified") val lastModified: String,
    @ColumnInfo(name = "status") val status: Drawable,
    @ColumnInfo(name = "comment") val comment: String
)