package com.versilistyson.welldone.domain.framework.datasource.user

import com.versilistyson.welldone.data.api.UserAuthenticationApi
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import retrofit2.Response

interface UserRemoteDataSource: BaseDataSource {
    fun signInUser(email: String, password: String) : Response<UserAuthenticationApi.Dto.AuthenticationResponse>
    fun signOutUser(token: String) : Response<Int>
    fun getUserDetails(token: String) : Response<UserAuthenticationApi.Dto.AuthenticationResponse>
    fun isTokenStillValid(token: String) : Response<Int>
}