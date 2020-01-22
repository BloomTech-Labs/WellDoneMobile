package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.domain.framework.entity.Entity
import retrofit2.Response

interface UserRepository {
    fun signInUser(email: String, password: String): Response<Entity.User>
    fun signOutUser(): Response<Int>
}