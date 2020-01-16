package com.versilistyson.welldone.domain.usecases

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.IUserAuthenticationRepository

class GetAuthenticateUser(private val repository: IUserAuthenticationRepository) : UseCase() {
    suspend fun signInUser(): Result<Entity.AuthenticatedUser> = repository.signInUser()
    suspend fun signOutUser(): Result<Int> = repository.signOutUser()
}