package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.domain.framework.datasource.user.UserRemoteDataSource
import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.UserAuthRepository

class UserAuthRepositoryImpl (private val remoteDataSource: UserRemoteDataSource) : UserAuthRepository {

    override fun signInUser(): Result<Entity.AuthenticatedUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signOutUser(): Result<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}