package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.domain.common.ResponseResult
import com.versilistyson.welldone.domain.common.RetrofitResult
import com.versilistyson.welldone.domain.framework.entity.Entity


interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String) : RetrofitResult<Entity.AuthenticationDetails>
    suspend fun storeToken(token: String): Boolean
//    suspend fun storeUserId(userId: Long): Boolean
//    fun getUserId(): Long
    fun getUserToken(): String?
}