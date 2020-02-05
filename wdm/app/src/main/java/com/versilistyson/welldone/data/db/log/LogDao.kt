package com.versilistyson.welldone.data.db.log

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(logs: List<LogData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(log: LogData)

    @Query("SELECT * FROM log_table WHERE sensor_id = :sensorId")
    fun getAllLogsBySensorId(sensorId: Long) : Flow<List<LogData>>

//    @Query("SELECT * FROM log_table WHERE log_id = :id")
//    fun getLogById(id: Int) : Flow<LogData>
}