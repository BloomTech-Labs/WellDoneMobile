package com.versilistyson.welldone.data.datasource.sensor

import androidx.lifecycle.LiveData
import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.common.Result

interface ISensorApiDataSource {
   suspend fun fetchSensorsByNetwork() : Result<List<SensorApi.Dto.SensorRecentResponse>>
    suspend fun fetchSensorsByDatabase() : LiveData<Result<List<SensorData.Sensor>>>
    suspend fun saveSensorsLocally()

    suspend fun fetchSensorsWhileOffline()
    suspend fun fetchSensorsWhileOnline()
}