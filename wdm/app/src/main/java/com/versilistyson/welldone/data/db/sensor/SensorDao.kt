package com.versilistyson.welldone.data.db.sensor

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SensorDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(key: String, sensors: List<SensorData>)

    @Delete
    fun deleteAll(key: String)

    @Query("SELECT * FROM sensor_table")
    fun getAll(key: String) : Flow<List<SensorData>>

    @Query("SELECT * FROM sensor_table WHERE sensor_id = :sensorId")
    fun getSensorBySensorId(key: String, sensorId: Long) : Flow<SensorData>
}