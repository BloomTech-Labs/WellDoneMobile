package com.versilistyson.welldone.domain.framework.datasource.user

import com.versilistyson.welldone.data.api.user.UserAuthenticationApi
import retrofit2.Response

interface AuthenticationRemoteDataSource {
    suspend fun signIn(email: String, password: String): Response<UserAuthenticationApi.Dto.AuthenticationResponse>
}