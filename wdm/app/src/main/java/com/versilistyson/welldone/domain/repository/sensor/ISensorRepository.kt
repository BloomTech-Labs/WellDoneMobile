package com.versilistyson.welldone.domain.repository.sensor

import com.versilistyson.welldone.domain.common.ResultState
import com.versilistyson.welldone.domain.entity.Entity

interface ISensorRepository {
    suspend fun fetchAllSensors(): ResultState<List<Entity.SensorRecentResponse>>
}