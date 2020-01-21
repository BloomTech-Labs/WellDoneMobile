package com.versilistyson.welldone.data.db.log

import android.graphics.drawable.Drawable
import androidx.room.*
import com.mapbox.mapboxsdk.style.expressions.Expression.array
import com.versilistyson.welldone.data.db.sensor.SensorData

sealed class LogData {
    @Entity(
        tableName = "log_table",
        foreignKeys = [ForeignKey(
            entity = SensorData.Sensor::class,
            parentColumns = ["id"],
            childColumns = ["sensor_id"],
            onDelete = ForeignKey.CASCADE
        )],
        indices = [Index(value = ["remote_id"], unique = true)]
    )
    data class OperatorLog(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "remote_id") val remoteId: Long,
        @ColumnInfo(name = "sensor_id") val sensorId: Long,
        @ColumnInfo(name = "date_filed") val dateFiled: String,
        @ColumnInfo(name = "last_modified") val lastModified: String,
        @ColumnInfo(name = "status") val status: Drawable,
        @ColumnInfo(name = "comment") val comment: String
    ) : LogData()
}