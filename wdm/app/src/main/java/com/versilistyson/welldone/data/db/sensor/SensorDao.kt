package com.versilistyson.welldone.data.db.sensor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SensorDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(sensors: List<SensorData>)

    @Query("SELECT * FROM sensor_table")
    suspend fun getAll() : LiveData<List<SensorData>>

    @Query("SELECT * FROM sensor_table WHERE id = :localId")
    suspend fun getSensorByLocalId(localId: Long) : LiveData<SensorData>

    @Query("SELECT remote_id FROM sensor_table WHERE id = :localId")
    suspend fun getRemoteIdByLocalId(localId: Long) : LiveData<Long>

    @Query("SELECT * FROM sensor_table WHERE remote_id = :remoteId")
    suspend fun getSensorByRemoteId(remoteId: Long) : LiveData<SensorData>

}