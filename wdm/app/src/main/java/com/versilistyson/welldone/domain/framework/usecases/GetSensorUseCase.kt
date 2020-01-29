package com.versilistyson.welldone.domain.framework.usecases

import com.dropbox.android.external.store4.ResponseOrigin
import com.dropbox.android.external.store4.StoreResponse
import com.google.android.gms.maps.model.LatLng
import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map


@InternalCoroutinesApi
class GetSensorUseCase(private val sensorRepository: SensorRepository, private val coroutineScope: CoroutineScope) : UseCase<Entity.Sensors, UseCase.None>() {


    override suspend fun run(params: None): Either<Failure, Entity.Sensors> {
        TODO("Figure out how to get it to function with flow. Might have to change the base use case around for this")
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