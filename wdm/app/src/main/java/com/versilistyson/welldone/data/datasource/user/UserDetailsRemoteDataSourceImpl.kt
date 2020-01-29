package com.versilistyson.welldone.data.datasource.user

import com.versilistyson.welldone.data.api.user.UserDetailsApi
import com.versilistyson.welldone.data.db.user.UserDetailsData
import com.versilistyson.welldone.domain.framework.datasource.user.UserDetailsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class UserDetailsRemoteDataSourceImpl @Inject constructor(userDetailsApi: UserDetailsApi):
    UserDetailsRemoteDataSource {

    override fun getUserDetails(): Response<UserDetailsApi.Dto.UserDetails> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveUserDetails(userDetails: UserDetailsData): Flow<UserDetailsData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}