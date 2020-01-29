package com.versilistyson.welldone.data.datasource.sensor

import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SensorLocalDataSourceImpl @Inject constructor(private val dao: SensorDao) : SensorLocalDataSource {

    override fun getSensors(key: StoreKey.SensorsKey): Flow<List<SensorData>> = dao.getAll()
    override suspend fun saveSensors(key: StoreKey.SensorsKey, sensors: List<SensorData>) = dao.saveAll(sensors)
}

