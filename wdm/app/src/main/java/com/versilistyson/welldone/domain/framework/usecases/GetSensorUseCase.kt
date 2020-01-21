package com.versilistyson.welldone.domain.framework.usecases

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository

class GetSensorUseCase(private val sensorRepository: SensorRepository) : UseCase<List<Entity.Sensor>, UseCase.NoParams>() {

    override suspend fun run(params: NoParams): Either<Failure, List<Entity.Sensor>> {
        return try {
            val sensorResponse = sensorRepository.fetchAllSensorsRemotely()
            if(sensorResponse.body() != null) {
                Either.Right(sensorResponse.body()!!)
            } else
                Either.Left(Failure.None)

        } catch(exp: Exception){
            Either.Left(GetSensorsFailure(exp))
        }
    }

    data class GetSensorsFailure(val error: Exception): Failure.FeatureFailure(error)

    fun handleFailure(failure: Failure){

    }
}