package com.versilistyson.welldone.data.db.log

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(logs: List<LogData>): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(log: LogData): Int

    @Query("SELECT * FROM log_table WHERE sensor_id = :sensorId")
    fun getAllLogsBySensorId(sensorId: Int) : Flow<List<LogData>>

    @Query("SELECT * FROM log_table WHERE log_id = :id")
    fun getLogById(id: Int) : Flow<LogData>
}