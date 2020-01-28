package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.MemoryPolicy
import com.dropbox.android.external.store4.StoreBuilder
import com.dropbox.android.external.store4.StoreRequest
import com.dropbox.android.external.store4.StoreResponse
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@FlowPreview
class SensorRepositoryImpl(
    private val localDataSource: SensorLocalDataSource,
    private val remoteDataSource: SensorRemoteDataSource) : SensorRepository {

    private val store =
        StoreBuilder
            .fromNonFlow<StoreKey.SensorsKey, List<SensorData>>{
                val sensors = mutableListOf<SensorData>()
                remoteDataSource.getSensors().body()?.forEach {
                    sensors.add(it.map())
                }
                sensors
            }
            .persister(
                reader = localDataSource::getSensors,
                writer = localDataSource::saveSensors
            ).cachePolicy(
                MemoryPolicy.builder()
                    .setExpireAfterAccess(8)
                    .setExpireAfterTimeUnit(TimeUnit.DAYS)
                    .build()
            ).build()

    //fetches fresh sensors, returns an error if it fails to load the data from the network
    override suspend fun fetchFreshSensors(): Flow<StoreResponse<List<SensorData>>>
            = store.stream(StoreRequest.fresh(StoreKey.SensorsKey()))

    override suspend fun fetchSensors(): Flow<StoreResponse<List<SensorData>>>
            = store.stream(StoreRequest.cached(StoreKey.SensorsKey(), true))
}