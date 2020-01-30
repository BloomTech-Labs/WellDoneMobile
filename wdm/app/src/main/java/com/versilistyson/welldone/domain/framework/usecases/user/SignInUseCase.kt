package com.versilistyson.welldone.domain.framework.usecases.user

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.common.ResponseResult
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.AuthenticationRepository
import com.versilistyson.welldone.domain.framework.usecases.common.UseCase
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthenticationRepository):
    UseCase<ResponseResult<Entity.AuthenticationDetails>, SignInUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, ResponseResult<Entity.AuthenticationDetails>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    data class Params(val email: String, val password: String)

    data class InvalidSignInCredentials(val e: Exception = Exception(Exception("Bad Authentication"))): Failure.FeatureFailure(e)
}