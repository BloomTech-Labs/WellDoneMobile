package com.versilistyson.welldone.data.db.log

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

sealed class LogData {
    @Entity(
        tableName = "log_table")
    data class OperatorLog(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val logId: Long,
        @ColumnInfo(name = "date_filed") val dateFiled: String,
        @ColumnInfo(name = "last_modified") val lastModified: String,
        @ColumnInfo(name = "status") val status: Drawable,
        @ColumnInfo(name = "comment") val comment: String
    ) : LogData()
}