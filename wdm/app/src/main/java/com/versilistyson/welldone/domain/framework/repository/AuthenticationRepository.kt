package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.domain.framework.entity.Entity.AuthenticationDetails

interface AuthenticationRepository {
    suspend fun login(email: String, password: String) : AuthenticationDetails
    suspend fun storeToken(token: String): Boolean
    suspend fun storeUserId(userId: Long): Boolean
    fun getUserId(): Long
    fun getUserToken(): String?
}