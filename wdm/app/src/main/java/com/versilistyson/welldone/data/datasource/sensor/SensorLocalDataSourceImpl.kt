package com.versilistyson.welldone.data.datasource.sensor

import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.data.util.generateKeys
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import kotlinx.coroutines.flow.Flow

class SensorLocalDataSourceImpl(private val dao: SensorDao) : SensorLocalDataSource {

    override fun getSensors(keyList: (List<SensorData>) -> List<Int>): Flow<List<SensorData>> =
        dao.getAll(keyList)

    val fn: (List<Any>) -> List<Int> = { list -> generateKeys(list) }
    val suspendFn: suspend ((List<Any>) -> List<Int>, List<SensorData>) -> Unit = { fn1, list ->
        dao.saveAll(fn1, list)
    }

    override suspend fun saveSensors(sensorList: List<SensorData>) = suspendFn(::generateKeys, sensorList)
}

