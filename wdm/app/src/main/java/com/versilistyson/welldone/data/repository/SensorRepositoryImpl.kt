package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.StoreRequest
import com.dropbox.android.external.store4.StoreResponse
import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.data.util.generateKeys
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

@ExperimentalCoroutinesApi
@FlowPreview
class SensorRepositoryImpl(
    private val localDataSource: SensorLocalDataSource,
    private val remoteDataSource: SensorRemoteDataSource
) : SensorRepository, ObjectsBaseRepository<SensorApi.Dto.SensorRecentResponse, SensorData>() {

    override suspend fun getObjectsRemotely(): Response<List<SensorApi.Dto.SensorRecentResponse>> =
        remoteDataSource.getSensors()

    override fun readObjectsFromPersistence(keyList: (List<SensorData>) -> List<Int>): Flow<List<SensorData>> =
        localDataSource.getSensors(keyList)

    override suspend fun writeObjectsToPersistence(keyList: (List<SensorData>) -> List<Int>, sensors: List<SensorData>) =
        localDataSource.saveSensors(keyList, sensors)

    //fetches fresh sensors, returns an error if it fails to load the data from the network
    override suspend fun fetchFreshSensors(): Flow<StoreResponse<List<SensorData>>> =
        objectsStoreBuilder.stream(StoreRequest.fresh(::generateKeys))

    override suspend fun fetchLocalSensors(): Flow<StoreResponse<List<SensorData>>> =
        objectsStoreBuilder.stream(StoreRequest.cached(::generateKeys, true))
}