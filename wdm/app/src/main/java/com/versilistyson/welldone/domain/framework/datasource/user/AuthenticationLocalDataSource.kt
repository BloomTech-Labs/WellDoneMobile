package com.versilistyson.welldone.domain.framework.datasource.user

interface AuthenticationLocalDataSource {
    suspend fun saveToken(token: String): Boolean
    suspend fun saveId(userId: Long): Boolean
    suspend fun deleteUserId(): Boolean
    suspend fun deleteToken(): Boolean
    fun getToken(): String?
    fun getUserId(): Long
}