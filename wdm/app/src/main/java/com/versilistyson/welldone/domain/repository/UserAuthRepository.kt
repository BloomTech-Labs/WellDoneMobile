package com.versilistyson.welldone.domain.repository

import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity

interface UserAuthRepository {
    fun signInUser(): Result<Entity.AuthenticatedUser>
    fun signOutUser(): Result<Int>
}