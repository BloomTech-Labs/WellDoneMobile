package com.versilistyson.welldone.domain.framework.usecases.sensor

import com.dropbox.android.external.store4.ResponseOrigin
import com.dropbox.android.external.store4.StoreResponse
import com.google.android.gms.maps.model.LatLng
import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.common.ResponseResult
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import com.versilistyson.welldone.domain.framework.usecases.common.FlowUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@InternalCoroutinesApi
class GetCacheSensorStreamUseCase(
    private val sensorRepository: SensorRepository
) : FlowUseCase<ResponseResult<Entity.Sensors>, FlowUseCase.None>() {

    override suspend fun run(params: None): Flow<Either<Failure, ResponseResult<Entity.Sensors>>> {
        return sensorRepository.cacheSensorStream().map { storeResponse ->
            when (storeResponse) {
                is StoreResponse.Data -> {
                    val sensorList = mutableListOf<Entity.Sensor>()
                    var latitude = 0.0
                    var longitude= 0.0
                    storeResponse.value.forEach { sensor ->
                        sensorList.add(sensor)
                        latitude += sensor.location.latitude
                        longitude += sensor.location.longitude
                    }
                    val newSensorEntity = Entity.Sensors(
                        sensorList,
                        LatLng(latitude / (sensorList.size + 1), longitude / (sensorList.size + 1))
                    )
                    Either.Right(
                        ResponseResult.Data(
                            newSensorEntity,
                            storeResponse.origin
                        )
                    )
                }
                is StoreResponse.Loading -> {
                    Either.Right(ResponseResult.Loading<Entity.Sensors>(storeResponse.origin))
                }
                is StoreResponse.Error -> {
                    when (storeResponse.origin) {
                        ResponseOrigin.Cache -> {
                            Either.Left(Failure.CacheFailure(storeResponse.error as Exception))
                        }
                        ResponseOrigin.Fetcher -> {
                            Either.Left(Failure.ServerFailure(storeResponse.error as Exception))
                        }
                        ResponseOrigin.Persister -> {
                            Either.Left(Failure.PersisterFailure(storeResponse.error as Exception))
                        }
                    }
                }
            }
        }
    }

    class GetCacheSensorStreamFailure(featureException: Exception = Exception("Cache Sensor Stream Failure")) : Failure.FeatureFailure(featureException)
}