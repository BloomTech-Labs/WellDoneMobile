package com.versilistyson.welldone.data.db.log

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(logs: List<LogData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(log: LogData)

    @Query("SELECT * FROM log_table")
    fun getLogs(): Flow<List<LogData>>

    @Query("SELECT * FROM log_table WHERE sensor_id = :sensorId")
    fun getAllLogsBySensorId(sensorId: Int) : Flow<List<LogData>>

    @Query("SELECT * FROM log_table WHERE log_id = :id")
    fun getLogById(id: Int) : Flow<LogData>
}