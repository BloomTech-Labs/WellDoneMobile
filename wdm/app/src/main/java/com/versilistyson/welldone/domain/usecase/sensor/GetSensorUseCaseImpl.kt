package com.versilistyson.welldone.domain.usecase.sensor

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.repository.sensor.ISensorRepository

class GetSensorUseCaseImpl(private val repository: ISensorRepository) : IGetSensorUseCase {
    override suspend fun getSensors(): Result<List<Entity.Sensor>> = repository.fetchAllSensors()
}