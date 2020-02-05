package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.*
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.data.util.MEMORY_CACHE_TIME
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class SensorRepositoryImpl @Inject constructor(
    private val localDataSource: SensorLocalDataSource,
    private val remoteDataSource: SensorRemoteDataSource
) : SensorRepository {

    val store =
        StoreBuilder
            .fromNonFlow<StoreKey.SensorsKey, List<SensorData>> {storekey ->
                val sensors = mutableListOf<SensorData>()
                val sensorSet = mutableSetOf<Int>()
                remoteDataSource.getSensors(storekey).body()?.forEach {
                    if(!sensorSet.contains(it.sensorId)) { //issue in backend where it duplicates assigned sensors
                        sensors.add(it.map())
                    }
                    sensorSet.add(it.sensorId)
                }
                sensors
            }
            .persister(
                reader = localDataSource::getSensors,
                writer = localDataSource::saveSensors
            ).cachePolicy(
                MemoryPolicy.builder()
                    .setMemorySize(100)
                    .setExpireAfterAccess(MEMORY_CACHE_TIME)
                    .setExpireAfterTimeUnit(TimeUnit.DAYS)
                    .build()
            ).build()

    private fun createSensorStream(storeRequest: StoreRequest<StoreKey.SensorsKey>): Flow<StoreResponse<List<Entity.Sensor>>> {
        return store.stream(storeRequest).map { storeResponse ->
            when (storeResponse) {
                is StoreResponse.Loading -> {
                    StoreResponse.Loading(storeResponse.origin)
                }
                is StoreResponse.Data -> {
                    val mappedSensorList = mutableListOf<Entity.Sensor>()
                    storeResponse.value.forEach { sensorData ->
                        mappedSensorList.add(sensorData.map())
                    }
                    StoreResponse.Data<List<Entity.Sensor>>(
                        mappedSensorList,
                        storeResponse.origin
                    )
                }
                is StoreResponse.Error -> {
                    StoreResponse.Error<List<Entity.Sensor>>(
                        storeResponse.error,
                        storeResponse.origin
                    )
                }
            }
        }
    }

    //fetches fresh sensors, returns an error if it fails to load the data from the network
    override fun freshSensorStream(): Flow<StoreResponse<List<Entity.Sensor>>> =
        createSensorStream(StoreRequest.fresh(StoreKey.SensorsKey()))

    override fun cacheSensorStream(): Flow<StoreResponse<List<Entity.Sensor>>> =
        createSensorStream(StoreRequest.cached(StoreKey.SensorsKey(), true))
}
