package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.StoreBuilder
import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import kotlinx.coroutines.flow.Flow

class SensorRepositoryImpl(
    private val localDataSource: SensorLocalDataSource,
    private val remoteDataSource: SensorRemoteDataSource
) : SensorRepository, BaseRepository<SensorApi.Dto.SensorRecentResponse,
        SensorData, Entity.Sensor>() {

    private val storeBuilder =
        StoreBuilder
            .fromNonFlow<(List<SensorData>) -> List<Int>, List<SensorData>> {
                val sensorList = mutableListOf<SensorData>()
                remoteDataSource.getSensors().body()?.forEach {
                    sensorList.add(it.map())
                }
                sensorList
            }.persister(
                reader = localDataSource::getSensors,
                writer = localDataSource::saveSensors
            )

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