package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.coroutines.flow.Flow

interface SensorRepository {
    suspend fun fetchSensors(): Flow<List<Entity.Sensor>>
    suspend fun fetchFreshSensors(): Flow<List<Entity.Sensor>>
    suspend fun fetchLocalSensors(): Flow<List<Entity.Sensor>>
}