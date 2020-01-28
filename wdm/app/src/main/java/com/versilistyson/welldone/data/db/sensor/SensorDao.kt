package com.versilistyson.welldone.data.db.sensor

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SensorDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(key: (List<SensorData>) -> List<Int>, sensors: List<SensorData>)

    @Delete
    fun deleteAll(key: (List<SensorData>) -> List<Int>)

    @Query("SELECT * FROM sensor_table")
    fun getAll(key: (List<SensorData>) -> List<Int>) : Flow<List<SensorData>>

    @Query("SELECT * FROM sensor_table WHERE sensor_id = :sensorId")
    fun getSensorBySensorId(key: (SensorData) -> Int, sensorId: Long) : Flow<SensorData>


}