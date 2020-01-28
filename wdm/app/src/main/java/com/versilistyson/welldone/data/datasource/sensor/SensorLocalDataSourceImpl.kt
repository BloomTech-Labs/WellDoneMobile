package com.versilistyson.welldone.data.datasource.sensor

import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import kotlinx.coroutines.flow.Flow

class SensorLocalDataSourceImpl(private val dao: SensorDao) : SensorLocalDataSource {

    override fun getSensors(key: String): Flow<List<SensorData>> =
        dao.getAll(key)

    override suspend fun saveSensors(key: String, sensors: List<SensorData>) =
        dao.saveAll(key, sensors)
}

