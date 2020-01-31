package com.versilistyson.welldone.domain.framework.usecases.user

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.repository.AuthenticationRepository
import com.versilistyson.welldone.domain.framework.usecases.common.UseCase
import javax.inject.Inject

class SaveUserTokenUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository):
    UseCase<Boolean, SaveUserTokenUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, Boolean> {
        return try {
            Either.Right(authenticationRepository.storeToken(params.tokenToStore))
        } catch (e: Exception) {
            Either.Left(Failure.PersisterFailure(e))
        }
    }

    data class Params(val tokenToStore: String)
}