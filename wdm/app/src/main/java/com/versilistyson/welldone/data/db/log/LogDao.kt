package com.versilistyson.welldone.data.db.log

import android.util.Log
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

    @Query("SELECT * FROM log_table WHERE remote_sensor_id = :remoteSensorId")
    suspend fun getAllLogsByRemoteSensorId(remoteSensorId: Int) : LiveData<List<LogData>>

    @Query("SELECT * FROM log_table WHERE id = :localId")
    suspend fun getLogByLocalId(localId: Int) : LiveData<LogData>
}