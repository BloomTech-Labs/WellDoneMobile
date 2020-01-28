package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.StoreBuilder
import com.dropbox.android.external.store4.StoreRequest
import com.dropbox.android.external.store4.StoreResponse
import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.data.util.generateKeys
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
@FlowPreview
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
            ).build()

//    override suspend fun fetchSensors(): Flow<StoreResponse<List<SensorData>>> =
//        storeBuilder.stream(StoreRequest.cached(::generateKeys, false))

    //fetches fresh sensors, returns an error if it fails to load the data from the network
    override suspend fun fetchFreshSensors(): Flow<StoreResponse<List<SensorData>>> =
        storeBuilder.stream(StoreRequest.fresh(::generateKeys))

    override suspend fun fetchLocalSensors(): Flow<StoreResponse<List<SensorData>>> =
        storeBuilder.stream(StoreRequest.cached(::generateKeys, true))
}