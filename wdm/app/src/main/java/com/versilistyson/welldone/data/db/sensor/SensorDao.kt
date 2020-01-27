package com.versilistyson.welldone.data.db.sensor

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SensorDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(sensors: List<SensorData>): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(sensor: SensorData): Int

    @Query("SELECT * FROM sensor_table")
    fun getAll() : Flow<List<SensorData>>

    @Query("SELECT * FROM sensor_table WHERE sensor_id = :sensorId")
    fun getSensorBySensorId(sensorId: Long) : Flow<SensorData>
}