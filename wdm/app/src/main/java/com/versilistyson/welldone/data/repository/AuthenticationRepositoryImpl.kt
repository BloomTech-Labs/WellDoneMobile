package com.versilistyson.welldone.data.repository

import android.content.Context
import com.versilistyson.welldone.data.datasource.user.AuthenticationLocalDataSourceImpl
import com.versilistyson.welldone.domain.framework.datasource.user.AuthenticationLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.user.AuthenticationRemoteDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity.AuthenticationDetails
import com.versilistyson.welldone.domain.framework.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val localDataSource: AuthenticationLocalDataSource,
    private val remoteDataSource: AuthenticationRemoteDataSource
): AuthenticationRepository {

    override suspend fun login(email: String, password: String): AuthenticationDetails {
        TODO()
    }

    override suspend fun storeToken(token: String): Boolean {
        return localDataSource.saveToken(token)
    }

    override suspend fun storeUserId(userId: Long): Boolean {
        return localDataSource.saveId(userId)
    }

    override fun getUserId(): Long {
        return localDataSource.getUserId()
    }

    override fun getUserToken(): String? {
        return localDataSource.getToken()
    }
}