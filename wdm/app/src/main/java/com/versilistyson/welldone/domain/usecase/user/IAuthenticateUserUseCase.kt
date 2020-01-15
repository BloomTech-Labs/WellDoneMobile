package com.versilistyson.welldone.domain.usecase.user

import com.versilistyson.welldone.domain.common.ResultState
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.usecase.BaseUseCase

interface IAuthenticateUserUseCase : BaseUseCase {
    suspend fun signInUser(): ResultState<Entity.AuthenticatedUser>
    suspend fun signOutUser(): ResultState<Int>
}