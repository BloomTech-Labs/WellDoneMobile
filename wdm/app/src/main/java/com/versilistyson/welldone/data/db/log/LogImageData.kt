package com.versilistyson.welldone.data.db.log

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.versilistyson.welldone.data.util.Mappable
import com.versilistyson.welldone.domain.framework.entity.Entity

@androidx.room.Entity(
    tableName = "image_table",
    foreignKeys = [ForeignKey(
        entity = LogData::class,
        parentColumns = ["log_id"],
        childColumns = ["log_id"],
        onDelete = ForeignKey.CASCADE)],
    indices = [Index("log_id", unique = true)]
)
data class LogImageData(
    @ColumnInfo(name = "image_link") val imageLink: String,
    @ColumnInfo(name = "caption") val caption: String,
    @ColumnInfo(name = "log_id") val logId: Long,
    @ColumnInfo(name = "imageDataId") @PrimaryKey(autoGenerate = false) val imageId: Long = 0
): Mappable<Entity.LogImage> {

    override fun map(): Entity.LogImage =
        Entity.LogImage(
            logId = logId,
            caption = caption,
            imageLink = imageLink,
            imageUrl = Uri.parse(imageLink)
        )
}