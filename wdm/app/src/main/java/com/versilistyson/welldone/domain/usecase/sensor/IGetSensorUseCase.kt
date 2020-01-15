package com.versilistyson.welldone.domain.usecase.sensor

import com.versilistyson.welldone.domain.common.ResultState
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.usecase.BaseUseCase

interface IGetSensorUseCase : BaseUseCase {
    suspend fun getSensors() : ResultState<List<Entity.SensorRecentResponse>>
}