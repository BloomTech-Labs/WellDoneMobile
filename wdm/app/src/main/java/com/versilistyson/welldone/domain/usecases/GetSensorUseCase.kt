package com.versilistyson.welldone.domain.usecases

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.ISensorRepository

class GetSensorUseCase(private val repository: ISensorRepository) : UseCase() {
    suspend fun getSensors(): Result<List<Entity.Sensor>> = repository.fetchAllSensors()
}