package com.versilistyson.welldone.domain.usecase.sensor

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.usecase.BaseUseCase

interface IGetSensorUseCase : BaseUseCase {
    suspend fun getSensors() : Result<List<Entity.Sensor>>
}