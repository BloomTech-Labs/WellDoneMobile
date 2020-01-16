package com.versilistyson.welldone.domain.usecase.user

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.usecase.BaseUseCase

interface IAuthenticateUserUseCase : BaseUseCase {
    suspend fun signInUser(): Result<Entity.AuthenticatedUser>
    suspend fun signOutUser(): Result<Int>
}