package com.versilistyson.welldone.domain.framework.repository

import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreResponse
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface SensorRepository {
    fun freshSensorStream(): Flow<StoreResponse<List<Entity.Sensor>>>
    fun sensorStream(): Flow<StoreResponse<List<Entity.Sensor>>>
}