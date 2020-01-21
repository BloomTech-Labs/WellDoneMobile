package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.domain.framework.entity.Entity
import retrofit2.Response

interface SensorRepository {
    suspend fun fetchAllSensorsRemotely(): Response<List<Entity.Sensor>>
    suspend fun fetchAllSensorsLocally(): Response<List<Entity.Sensor>>
    suspend fun fetchSensorLocally(): Response<List<Entity.Sensor>>
    suspend fun saveAllSensorsLocally(sensors: List<Entity.Sensor>): Response<List<Entity.Sensor>>
    suspend fun updateSensor(sensor: Entity.Sensor): Response<Entity.Sensor>
}