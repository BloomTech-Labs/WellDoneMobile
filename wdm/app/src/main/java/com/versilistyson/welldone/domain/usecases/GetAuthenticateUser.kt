package com.versilistyson.welldone.domain.usecases

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.repository.UserAuthRepository

class GetAuthenticateUser(private val userRepository: UserAuthRepository) : UseCase() {

    suspend fun signInUser(): Result<Entity.AuthenticatedUser> = userRepository.signInUser()
    suspend fun signOutUser(): Result<Int> = userRepository.signOutUser()

}