package com.versilistyson.welldone.domain.framework.datasource.user

interface AuthenticationRemoteDataSource {
    fun signIn(email: String, password: String)
    fun isTokenValid(token: String): Boolean
}