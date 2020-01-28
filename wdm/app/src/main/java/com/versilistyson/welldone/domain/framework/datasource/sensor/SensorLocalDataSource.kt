package com.versilistyson.welldone.domain.framework.datasource.sensor

import com.versilistyson.welldone.data.db.StoreKey
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import kotlinx.coroutines.flow.Flow

interface SensorLocalDataSource: BaseDataSource {
    fun getSensors(key: StoreKey.SensorsKey): Flow<List<SensorData>>
    suspend fun saveSensors(key: StoreKey.SensorsKey, sensors: List<SensorData>)
}