package com.versilistyson.welldone.data.db.log

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LogImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(logs: List<LogImageData>): Int

    @Query("SELECT * FROM image_table WHERE log_id = :logId")
    fun getAllLogImagesByLogId(logId: Long) : Flow<List<LogImageData>>
}