package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.data.datasource.sensor.SensorDataSource
import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.ISensorRepository

class SensorRepository(dataSource: SensorDataSource) : ISensorRepository {
    override suspend fun fetchAllSensors(): Result<List<Entity.Sensor>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}