package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.domain.framework.datasource.user.UserDetailsLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.user.UserDetailsRemoteDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.UserDetailRepository
import retrofit2.Response

class UserDetailRepositoryImpl (
    private val localDataSource: UserDetailsLocalDataSource,
    private val remoteDataSource: UserDetailsRemoteDataSource
) : UserDetailRepository {

}