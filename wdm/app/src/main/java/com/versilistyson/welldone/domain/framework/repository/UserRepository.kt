package com.versilistyson.welldone.domain.framework.repository

import android.content.Context
import com.versilistyson.welldone.domain.framework.entity.Entity
import retrofit2.Response

interface UserRepository {
    fun saveToken(context: Context, uid: String, token: String)
    fun signInUser(email: String, password: String): Response<Entity.AuthenticatedUser>
    fun signOutUser(): Response<Int>
}