package com.versilistyson.welldone.domain.framework.datasource.user

interface UserAuthenticationLocalDataSource {
    suspend fun saveToken(token: String): Boolean
    suspend fun saveId(id: String): Boolean
    suspend fun deleteUserId(): Boolean
    suspend fun deleteToken(userId: String): Boolean
}