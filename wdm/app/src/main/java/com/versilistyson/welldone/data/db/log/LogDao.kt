package com.versilistyson.welldone.data.db.log

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LogDao {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(logs: List<LogData>): List<LogData>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(log: LogData): LogData

    @Query("SELECT * FROM log_table WHERE sensor_id = :sensorId")
    suspend fun getAllLogsBySensorId(sensorId: Int) : LiveData<List<LogData>>

    @Query("SELECT * FROM log_table WHERE log_id = :id")
    suspend fun getLogById(id: Int) : LiveData<LogData>
}