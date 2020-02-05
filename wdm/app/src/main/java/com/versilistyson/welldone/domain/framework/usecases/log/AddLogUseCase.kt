package com.versilistyson.welldone.domain.framework.usecases.log

import com.versilistyson.welldone.data.api.log.LogApi
import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.common.RetrofitResult
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.LogRepository
import com.versilistyson.welldone.domain.framework.usecases.common.UseCase
import javax.inject.Inject

class AddLogUseCase @Inject constructor(private val logRepository: LogRepository): UseCase<Entity.LogDetails?, AddLogUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, Entity.LogDetails?> {
        return try {
            val logResult = logRepository.addLog(LogApi.Dto.LogToPost(params.sensorId, params.comment))
            when(logResult){
                is RetrofitResult.Data -> {
                    Either.Right(logResult.data)
                }
                is RetrofitResult.Error -> {
                    Either.Left(AddLogFailure())
                }
            }
        } catch(e: Exception){
            Either.Left(AddLogFailure(e))
        }
    }

    data class AddLogFailure(val e: Exception = Exception()): Failure.FeatureFailure(e)

    data class Params(val sensorId: Long, val comment: String)
}