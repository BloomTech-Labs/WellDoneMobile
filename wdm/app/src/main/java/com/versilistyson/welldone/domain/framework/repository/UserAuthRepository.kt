package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.framework.entity.Entity

interface UserAuthRepository {
    fun signInUser(): Result<Entity.AuthenticatedUser>
    fun signOutUser(): Result<Int>
}