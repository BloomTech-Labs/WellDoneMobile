package com.versilistyson.welldone.domain.framework.usecases.sensor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map

@InternalCoroutinesApi
class GetCacheSensorStreamUseCase(
    private val sensorRepository: SensorRepository
) : FlowUseCase<ResponseResult<Entity.Sensors>, FlowUseCase.None>() {

    private val _sensorLiveData: MutableLiveData<Either<Failure, ResponseResult<Entity.Sensors>>> by lazy {
        MutableLiveData<Either<Failure, ResponseResult<Entity.Sensors>>>()
    }
    val sensorLiveData: LiveData<Either<Failure, ResponseResult<Entity.Sensors>>>
        get() = _sensorLiveData

    override suspend fun run(params: None) {

        val flowCollector = object: FlowCollector<StoreResponse<List<Entity.Sensor>>> {
            override suspend fun emit(storeResponse: StoreResponse<List<Entity.Sensor>>) {
                when (storeResponse) {
                    is StoreResponse.Data -> {
                        val sensorList = mutableListOf<Entity.Sensor>()
                        var latitude = 0.0
                        var longitude = 0.0
                        storeResponse.value.forEach { sensor ->
                            sensorList.add(sensor)
                            latitude += sensor.location.latitude
                            longitude += sensor.location.longitude
                        }
                        val newSensorEntity = Entity.Sensors(
                            sensorList,
                            LatLng(
                                latitude / (sensorList.size + 1),
                                longitude / (sensorList.size + 1)
                            )
                        )
                        _sensorLiveData.value = Either.Right(
                            ResponseResult.Data(
                                newSensorEntity,
                                storeResponse.origin
                            )
                        )
                    }
                    is StoreResponse.Loading -> {
                        _sensorLiveData.value =
                            Either.Right(ResponseResult.Loading(storeResponse.origin))
                    }
                    is StoreResponse.Error -> {
                        when (storeResponse.origin) {
                            ResponseOrigin.Cache -> {
                                _sensorLiveData.value =
                                    Either.Left(Failure.CacheFailure(storeResponse.error as Exception))
                            }
                            ResponseOrigin.Fetcher -> {
                                _sensorLiveData.value =
                                    Either.Left(Failure.ServerFailure(storeResponse.error as Exception))
                            }
                            ResponseOrigin.Persister -> {
                                _sensorLiveData.value =
                                    Either.Left(Failure.PersisterFailure(storeResponse.error as Exception))
                            }
                        }
                    }
                }
            }
        }
        sensorRepository.cacheSensorStream().collect(flowCollector)
    }

    class GetCacheSensorStreamFailure(featureException: Exception = Exception("Cache Sensor Stream Failure")) : Failure.FeatureFailure(featureException)
}