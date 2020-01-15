package com.versilistyson.welldone.domain.repository.user

import com.versilistyson.welldone.domain.common.ResultState
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.repository.IBaseRepository

interface IUserAuthenticationRepository : IBaseRepository {
    fun signInUser(): ResultState<Entity.AuthenticatedUser>
    fun signOutUser(): ResultState<Int>
}