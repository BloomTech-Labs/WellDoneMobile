package com.versilistyson.welldone.data.db.log

import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@androidx.room.Entity(
    tableName = "picture_table",
    foreignKeys = [ForeignKey(
        entity = LogData::class,
        parentColumns = ["log_id"],
        childColumns = ["log_id"],
        onDelete = ForeignKey.CASCADE)],
    indices = [Index("log_id", unique = true)]
)
data class ImageData (
    @ColumnInfo(name = "link") val imageLink: String,
    @ColumnInfo(name = "pictureSubtitle") val caption: String?,
    @ColumnInfo(name = "log_id") val logId: Long,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val pictureId: Long = 0
)