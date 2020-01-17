package com.versilistyson.welldone.domain.usecases

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.repository.SensorRepository

class GetSensorUseCase(private val sensorRepository: SensorRepository) : UseCase() {
    suspend fun getSensors(): Result<List<Entity.Sensor>> = sensorRepository.fetchAllSensorsLocally()
}