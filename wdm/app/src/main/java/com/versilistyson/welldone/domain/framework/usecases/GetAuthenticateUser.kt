package com.versilistyson.welldone.domain.framework.usecases

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.UserAuthRepository

class GetAuthenticateUser(private val userRepository: UserAuthRepository) : UseCase<Entity.AuthenticatedUser, UseCase.NoParams>() {

    override suspend fun run(params: NoParams): Either<Failure, Entity.AuthenticatedUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}