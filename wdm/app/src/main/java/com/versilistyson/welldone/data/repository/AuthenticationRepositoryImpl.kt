package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.domain.common.RetrofitResult
import com.versilistyson.welldone.domain.framework.datasource.user.AuthenticationLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.user.AuthenticationRemoteDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val localDataSource: AuthenticationLocalDataSource,
    private val remoteDataSource: AuthenticationRemoteDataSource
): AuthenticationRepository {

    override suspend fun signIn(email: String, password: String): RetrofitResult<Entity.AuthenticationDetails> {
        val response = remoteDataSource.signIn(email, password)
        return if(response.isSuccessful) {
            RetrofitResult.Data(response.body()?.map())
        } else {
            RetrofitResult.Error(response.message(), response.code())
        }
    }

    override suspend fun storeToken(token: String): Boolean {
        return localDataSource.saveToken(token)
    }

//    override suspend fun storeUserId(userId: Long): Boolean {
//        return localDataSource.saveId(userId)
//    }
//
//    override fun getUserId(): Long {
//        return localDataSource.getUserId()
//    }

    override fun getUserToken(): String? {
        return localDataSource.getToken()
    }
}