package com.versilistyson.welldone.domain.framework.repository

import androidx.lifecycle.LiveData
import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.framework.entity.Entity

interface SensorRepository {

    suspend fun fetchAllSensorsRemotely(): Result<LiveData<List<Entity.Sensor>>>
    suspend fun fetchAllSensorsLocally(): Result<Entity.Sensors?>
    suspend fun fetchSensorLocally(): Result<Entity.Sensors?>
    suspend fun saveAllSensorsLocally(sensors: List<Entity.Sensor>): Result<List<Entity.Sensor>?>
    suspend fun updateSensor(sensor: Entity.Sensor): Result<Entity.Sensor?>
}