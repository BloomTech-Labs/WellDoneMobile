package com.versilistyson.welldone.domain.framework.usecases.user

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.AuthenticationRepository
import com.versilistyson.welldone.domain.framework.repository.UserDetailRepository
import com.versilistyson.welldone.domain.framework.usecases.UseCase
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthenticationRepository):
    UseCase<Entity.UserDetails, SignInUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, Entity.UserDetails> {
        TODO()
    }

    data class Params(val email: String, val password: String)

    data class InvalidSignInCredentials(val e: Exception = Exception(Exception("Bad Authentication"))): Failure.FeatureFailure(e)
}