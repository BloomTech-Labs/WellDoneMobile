package com.versilistyson.welldone.domain

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity

interface IUserAuthenticationRepository {
    fun signInUser(): Result<Entity.AuthenticatedUser>
    fun signOutUser(): Result<Int>
}