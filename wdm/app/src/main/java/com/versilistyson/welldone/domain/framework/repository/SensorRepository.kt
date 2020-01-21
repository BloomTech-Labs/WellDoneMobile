package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.domain.framework.entity.Entity
import retrofit2.Response

interface SensorRepository {
    suspend fun fetchAllSensorsRemotely(): Response<Entity.Sensors>
    suspend fun fetchAllSensorsLocally(): Response<Entity.Sensors>
    suspend fun fetchSensorLocally(): Response<Entity.Sensors>
    suspend fun saveAllSensorsLocally(sensors: List<Entity.Sensor>): Response<List<Entity.Sensor>>
    suspend fun updateSensor(sensor: Entity.Sensor): Response<Entity.Sensor>
}