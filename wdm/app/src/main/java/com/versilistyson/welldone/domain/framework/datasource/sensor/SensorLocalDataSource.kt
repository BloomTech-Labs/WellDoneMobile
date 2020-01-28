package com.versilistyson.welldone.domain.framework.datasource.sensor

import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import kotlinx.coroutines.flow.Flow

interface SensorLocalDataSource: BaseDataSource {
    fun getSensors(key: String): Flow<List<SensorData>>
    suspend fun saveSensors(key: String, sensors: List<SensorData>)
}