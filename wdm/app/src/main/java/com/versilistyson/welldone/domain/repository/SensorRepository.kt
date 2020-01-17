package com.versilistyson.welldone.domain.repository

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity

interface SensorRepository {
    suspend fun fetchAllSensorsRemotely(): Result<List<Entity.Sensor>>
    suspend fun fetchAllSensorsLocally(): Result<List<Entity.Sensor>>
    suspend fun fetchSensorLocally(): Result<List<Entity.Sensor>>
    suspend fun saveAllSensorsLocally(sensors: List<Entity.Sensor>): Result<List<Entity.Sensor>>
    suspend fun updateSensor(sensor: Entity.Sensor): Result<Entity.Sensor>
}