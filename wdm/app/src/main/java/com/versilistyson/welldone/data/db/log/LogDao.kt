package com.versilistyson.welldone.data.db.log

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(logs: List<LogData>): Flow<List<LogData>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(log: LogData): Flow<LogData>

    @Query("SELECT * FROM log_table WHERE sensor_id = :sensorId")
    suspend fun getAllLogsBySensorId(sensorId: Int) : Flow<List<LogData>>

    @Query("SELECT * FROM log_table WHERE log_id = :id")
    suspend fun getLogById(id: Int) : Flow<LogData>
}