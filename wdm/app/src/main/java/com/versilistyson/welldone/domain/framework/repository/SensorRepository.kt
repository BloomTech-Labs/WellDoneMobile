package com.versilistyson.welldone.domain.framework.repository

import com.dropbox.android.external.store4.StoreResponse
import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.coroutines.flow.Flow

interface SensorRepository {
    fun freshSensorStream(): Flow<StoreResponse<List<Entity.Sensor>>>
    fun cacheSensorStream(): Flow<StoreResponse<List<Entity.Sensor>>>
}