package com.versilistyson.welldone.data.datasource.sensor

import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.coroutines.flow.Flow

class SensorLocalDataSourceImpl(dao: SensorDao) : SensorLocalDataSource {
    override suspend fun getSensors(): Flow<List<SensorData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveSensors(sensors: List<SensorData>): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}