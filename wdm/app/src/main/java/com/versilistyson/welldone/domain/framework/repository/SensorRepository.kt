package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.framework.entity.Entity

interface SensorRepository {

    suspend fun fetchAllSensorsRemotely(): Result<List<Entity.Sensor>>
    suspend fun fetchAllSensorsLocally(): Result<List<Entity.Sensor>>
    suspend fun fetchSensorLocally(sensorId: Long): Result<Entity.Sensor>
    suspend fun saveAllSensorsLocally(sensors: List<SensorData>): Result<List<Entity.Sensor>>
    suspend fun updateSensor(sensor: Entity.Sensor): Result<Entity.Sensor>
}