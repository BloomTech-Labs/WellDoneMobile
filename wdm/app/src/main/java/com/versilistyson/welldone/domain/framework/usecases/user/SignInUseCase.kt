package com.versilistyson.welldone.domain.framework.usecases.user

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.repository.UserRepository
import com.versilistyson.welldone.domain.framework.usecases.UseCase
import com.versilistyson.welldone.domain.framework.entity.Entity

class SignInUseCase(private val userRepository: UserRepository) : UseCase<Entity.AuthenticatedUser, SignInUseCase.Params>() {

    data class Params(val email: String, val password: String)

    override suspend fun run(params: Params): Either<Failure, Entity.AuthenticatedUser> {
    }


}