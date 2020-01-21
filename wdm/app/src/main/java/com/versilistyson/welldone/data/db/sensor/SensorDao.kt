package com.versilistyson.welldone.data.db.sensor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SensorDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(sensors: List<SensorData.Sensor>)

    @Query("SELECT * FROM sensor_table")
    fun getAll() : LiveData<List<SensorData.Sensor>>

    @Query("SELECT * FROM sensor_table WHERE id = :localId")
    fun get(localId: Int) : LiveData<SensorData.Sensor>

}