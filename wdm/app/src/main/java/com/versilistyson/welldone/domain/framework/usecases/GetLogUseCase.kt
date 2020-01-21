package com.versilistyson.welldone.domain.framework.usecases

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.LogRepository

class GetLogUseCase(private val logRepository: LogRepository): UseCase<List<Entity.Log>, GetLogUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<Entity.Log>> {
        return try {
            val logResponse = logRepository.fetchAllLogsRemotely(params.sensorId)
            if(logResponse.body() != null) {
                Either.Right(logResponse.body()!!)
            } else
                Either.Left(Failure.None)

        } catch(exp: Exception){
            Either.Left(GetLogsFailure(exp))
        }
    }

    data class Params(val sensorId: Long)

    data class GetLogsFailure(val exc: Exception): Failure.FeatureFailure(exc)
}