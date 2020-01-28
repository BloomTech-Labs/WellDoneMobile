package com.versilistyson.welldone.data.datasource.sensor

import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import kotlinx.coroutines.flow.Flow

class SensorLocalDataSourceImpl(private val dao: SensorDao) : SensorLocalDataSource {

    override fun getSensors(keyList: (List<SensorData>) -> List<Int>): Flow<List<SensorData>> =
        dao.getAll(keyList)

    override suspend fun saveSensors(fn1: (List<SensorData>) -> List<Int>, sensors: List<SensorData>)
            = dao.saveAll(fn1, sensors)
}

