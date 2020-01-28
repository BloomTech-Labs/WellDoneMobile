package com.versilistyson.welldone.domain.framework.datasource.user

import com.versilistyson.welldone.data.api.user.UserDetailsApi
import com.versilistyson.welldone.data.db.user.UserDetailsData
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserDetailsRemoteDataSource: BaseDataSource {
    fun getUserDetails() : Response<UserDetailsApi.Dto.UserDetails>
    fun saveUserDetails(userDetails: UserDetailsData) : Flow<UserDetailsData>
}