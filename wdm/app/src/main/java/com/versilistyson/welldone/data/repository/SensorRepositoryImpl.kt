package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.StoreBuilder
import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import kotlinx.coroutines.flow.Flow

class SensorRepositoryImpl(
    private val sensorApi: SensorApi,
    private val sensorDao: SensorDao
) : SensorRepository, BaseRepository<SensorApi.Dto.SensorRecentResponse,
        SensorData, Entity.Sensor>() {

    private val storeBuilder = StoreBuilder
    override suspend fun fetchSensors(): Flow<List<Entity.Sensor>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetchFreshSensors(): Flow<List<Entity.Sensor>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetchLocalSensors(): Flow<List<Entity.Sensor>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}