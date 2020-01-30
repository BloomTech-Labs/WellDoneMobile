package com.versilistyson.welldone.domain.framework.datasource.user

import com.versilistyson.welldone.data.api.user.UserAuthenticationApi
import com.versilistyson.welldone.data.db.user.UserDetailsData
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import retrofit2.Response

interface UserDetailsRemoteDataSource: BaseDataSource {
    fun getUserDetails(token: String) : Response<UserDetailsData?>
    fun saveUserDetails(token: String) : Response<UserDetailsData?>
}