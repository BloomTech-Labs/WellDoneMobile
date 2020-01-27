package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.domain.framework.datasource.user.UserDetailsLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.user.UserDetailsRemoteDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.UserDetailRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class UserDetailRepositoryImpl (
    private val localDataSource: UserDetailsLocalDataSource,
    private val remoteDataSource: UserDetailsRemoteDataSource
) : UserDetailRepository {

    override fun fetchUserDetails(): Flow<Entity.UserDetails> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchFreshUserDetails(): Flow<Entity.UserDetails> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchLocalUserDetails(): Flow<Entity.UserDetails> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}