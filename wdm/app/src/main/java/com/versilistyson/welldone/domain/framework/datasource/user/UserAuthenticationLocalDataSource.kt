package com.versilistyson.welldone.domain.framework.datasource.user

interface UserAuthenticationLocalDataSource {
    fun saveToken(token: String)
    fun saveId(id: String)
    fun deleteUserId()
    fun deleteToken(userId: String)
}