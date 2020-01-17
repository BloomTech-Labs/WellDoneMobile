package com.versilistyson.welldone.data.db.log

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.versilistyson.welldone.data.db.sensor.SensorData

@Dao
interface LogDao {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(logs: List<LogData>)

    @Query("SELECT * FROM sensor_table")
    fun getAll() : LiveData<List<SensorData>>

    @Query("SELECT * FROM sensor_table WHERE id = :localId")
    fun get(localId: Int) : LiveData<SensorData>
}