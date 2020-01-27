package com.versilistyson.welldone.data.datasource.sensor

import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.coroutines.flow.Flow

class SensorLocalDataSourceImpl(private val dao: SensorDao) : SensorLocalDataSource {
    override suspend fun getSensors(): Flow<List<SensorData>> {
        return dao.getAll()
    }

    override suspend fun saveSensors(sensors: List<SensorData>): Int  {
        return dao.saveAll(sensors)
    }
}