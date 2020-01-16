package com.versilistyson.welldone.domain.repository.user

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity
import com.versilistyson.welldone.domain.repository.IBaseRepository

interface IUserAuthenticationRepository : IBaseRepository {
    fun signInUser(): Result<Entity.AuthenticatedUser>
    fun signOutUser(): Result<Int>
}