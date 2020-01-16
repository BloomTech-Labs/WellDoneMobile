package com.versilistyson.welldone.domain.usecase.user

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.repository.user.IUserAuthenticationRepository

class AuthenticateUserUseCase(private val repository: IUserAuthenticationRepository) : IAuthenticateUserUseCase {
    override suspend fun signInUser(): Result<Entity.AuthenticatedUser> = repository.signInUser()
    override suspend fun signOutUser(): Result<Int> = repository.signOutUser()
}