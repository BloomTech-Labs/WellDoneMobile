package com.versilistyson.welldone.domain.framework.repository

import com.dropbox.android.external.store4.StoreResponse
import com.versilistyson.welldone.data.db.sensor.SensorData
import kotlinx.coroutines.flow.Flow

interface SensorRepository {
//    suspend fun fetchSensors(): Flow<StoreResponse<List<SensorData>>>
    suspend fun fetchFreshSensors(): Flow<StoreResponse<List<SensorData>>>
    suspend fun fetchLocalSensors(): Flow<StoreResponse<List<SensorData>>>
}