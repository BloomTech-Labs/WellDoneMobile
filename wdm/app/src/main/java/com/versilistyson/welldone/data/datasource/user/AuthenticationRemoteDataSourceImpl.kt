package com.versilistyson.welldone.data.datasource.user

import com.versilistyson.welldone.data.api.user.UserAuthenticationApi
import com.versilistyson.welldone.domain.framework.datasource.user.AuthenticationRemoteDataSource
import retrofit2.Response

class AuthenticationRemoteDataSourceImpl(private val api: UserAuthenticationApi): AuthenticationRemoteDataSource {

    override suspend fun signIn(email: String, password: String): Response<UserAuthenticationApi.Dto.AuthenticationResponse> {
        return api.login(email, password)
    }

}