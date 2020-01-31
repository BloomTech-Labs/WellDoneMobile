package com.versilistyson.welldone.domain.framework.usecases.user

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.repository.AuthenticationRepository
import com.versilistyson.welldone.domain.framework.usecases.common.UseCase
import java.lang.Exception
import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    UseCase<Boolean, SaveUserIdUseCase.Params>() {

    data class Params(val userIdToStore: Long)

    override suspend fun run(params: Params): Either<Failure, Boolean> {
        return try {
            Either.Right(authenticationRepository.storeUserId(params.userIdToStore))
        } catch (e: Exception) {
            Either.Left(Failure.PersisterFailure(e))
        }

    }
}