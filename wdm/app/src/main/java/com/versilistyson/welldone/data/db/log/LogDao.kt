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

    @Query("SELECT * FROM log_table WHERE sensor_id = :sensorId")
    fun getAllLogsBySensorId(sensorId: Int) : LiveData<List<LogData.OperatorLog>>

    @Query("SELECT * FROM log_table WHERE id = :localId")
    fun getLogById(localId: Int) : LiveData<LogData.OperatorLog>
}