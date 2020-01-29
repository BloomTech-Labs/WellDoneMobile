package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.*
import com.versilistyson.welldone.data.db.WellDoneDatabase
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class SensorRepositoryImpl @Inject constructor(
    private val localDataSource: SensorLocalDataSource,
    private val remoteDataSource: SensorRemoteDataSource) : SensorRepository {

    val store =
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
                    .setMemorySize(100)
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