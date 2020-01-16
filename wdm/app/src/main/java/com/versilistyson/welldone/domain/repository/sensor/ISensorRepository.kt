package com.versilistyson.welldone.domain.repository.sensor

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity

interface ISensorRepository {
    suspend fun fetchAllSensors(): Result<List<Entity.Sensor>>
}