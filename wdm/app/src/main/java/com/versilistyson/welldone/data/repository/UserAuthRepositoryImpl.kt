package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.domain.framework.datasource.user.UserRemoteDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.UserAuthRepository
import retrofit2.Response

class UserAuthRepositoryImpl (private val remoteDataSource: UserRemoteDataSource) : UserAuthRepository {

    override fun signInUser(): Response<Entity.AuthenticatedUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signOutUser(): Response<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}