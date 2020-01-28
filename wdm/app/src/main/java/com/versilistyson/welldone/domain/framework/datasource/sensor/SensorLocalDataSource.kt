package com.versilistyson.welldone.domain.framework.datasource.sensor

import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import kotlinx.coroutines.flow.Flow

interface SensorLocalDataSource: BaseDataSource {
    fun getSensors(keyList: (List<SensorData>) -> List<Int>): Flow<List<SensorData>>
    suspend fun saveSensors(keyList: (List<SensorData>) -> List<Int>, sensors: List<SensorData>)
}