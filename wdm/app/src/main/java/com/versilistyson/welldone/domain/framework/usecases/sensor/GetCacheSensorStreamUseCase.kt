package com.versilistyson.welldone.domain.framework.usecases.sensor

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
        sensorRepository.cacheSensorStream().map { storeResponse ->
            when (storeResponse) {
                is StoreResponse.Data -> {
                    val sensorList = mutableListOf<Entity.Sensor>()
                    var latitude: Double = 0.0
                    var longitude: Double = 0.0
                    storeResponse.value.forEach { sensor ->
                        sensorList.add(sensor)
                        latitude += sensor.location.latitude
                        longitude += sensor.location.longitude
                    }
                    val newSensorEntity = Entity.Sensors(
                        sensorList,
                        LatLng(latitude / (sensorList.size + 1), longitude / (sensorList.size + 1))
                    )
                    return@map Either.Right(
                        ResponseResult.Data<Entity.Sensors>(
                            newSensorEntity,
                            storeResponse.origin
                        )
                    )
                }
                is StoreResponse.Loading -> {
                    return@map Either.Right(ResponseResult.Loading<Entity.Sensors>(storeResponse.origin))
                }
                is StoreResponse.Error -> {

                }
            }
        }
    }


}


//return try {
//            val sensorResponse = sensorRepository.fetchFreshSensors()
//            if(sensorResponse.body() != null) {
//                Either.Right(sensorResponse.body()!!)
//            } else
//                Either.Left(Failure.None)
//
//        } catch(exp: Exception){
//            Either.Left(GetSensorsFailure(exp))
//        }