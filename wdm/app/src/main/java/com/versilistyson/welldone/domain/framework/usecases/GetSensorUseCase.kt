package com.versilistyson.welldone.domain.framework.usecases

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository

class GetSensorUseCase(private val sensorRepository: SensorRepository) : UseCase() {
    suspend fun getSensors(): Result<List<Entity.Sensor>> = sensorRepository.fetchAllSensorsLocally()
}