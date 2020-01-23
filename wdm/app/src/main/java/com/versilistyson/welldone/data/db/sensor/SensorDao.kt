package com.versilistyson.welldone.data.db.sensor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.versilistyson.welldone.domain.framework.entity.Entity

@Dao
interface SensorDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(sensors: List<SensorData>): List<SensorData>

    @Query("SELECT * FROM sensor_table")
    suspend fun getAll() : List<SensorData>

    @Query("SELECT * FROM sensor_table WHERE sensor_id = :sensorId")
    suspend fun getSensorBySensorId(sensorId: Int) : SensorData

}