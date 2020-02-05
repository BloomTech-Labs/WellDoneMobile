package com.versilistyson.welldone.domain.framework.usecases.log

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.LogImageRepository
import com.versilistyson.welldone.domain.framework.repository.LogRepository
import com.versilistyson.welldone.domain.framework.usecases.common.UseCase
import javax.inject.Inject

class GetLogsUseCase @Inject constructor(private val logRepository: LogRepository,
                                         private val logImageRepository: LogImageRepository): UseCase<List<Entity.LogDetails>, GetLogsUseCase.Params>()
{

    override suspend fun run(params: Params): Either<Failure, List<Entity.LogDetails>> {
        TODO()
    }

    data class Params(val sensorId: Long)

    data class GetLogsFailure(val exc: Exception): Failure.FeatureFailure(exc)
}