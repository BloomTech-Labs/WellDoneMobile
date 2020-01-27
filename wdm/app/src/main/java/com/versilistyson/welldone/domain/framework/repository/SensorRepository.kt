package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.coroutines.flow.Flow

interface SensorRepository {
    suspend fun fetchSensors(): Flow<List<Entity.Sensor>>
    suspend fun fetchFreshSensors(): Flow<List<Entity.Sensor>>
    suspend fun fetchLocalSensors(): Flow<List<Entity.Sensor>>
}