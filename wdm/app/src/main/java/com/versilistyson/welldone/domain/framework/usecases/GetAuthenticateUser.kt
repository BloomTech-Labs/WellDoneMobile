package com.versilistyson.welldone.domain.framework.usecases

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.UserAuthRepository

class GetAuthenticateUser(private val userRepository: UserAuthRepository) : UseCase() {

    suspend fun signInUser(): Result<Entity.AuthenticatedUser> = userRepository.signInUser()
    suspend fun signOutUser(): Result<Int> = userRepository.signOutUser()

}