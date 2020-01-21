package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.domain.framework.entity.Entity
import retrofit2.Response

interface UserAuthRepository {
    fun signInUser(): Response<Entity.AuthenticatedUser>
    fun signOutUser(): Response<Int>
}