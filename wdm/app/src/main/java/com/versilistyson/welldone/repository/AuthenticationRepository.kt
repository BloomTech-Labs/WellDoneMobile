package com.versilistyson.welldone.repository

import com.versilistyson.welldone.data.remote.AuthenticationService
import com.versilistyson.welldone.data.remote.dto.AuthenticationRequest
import com.versilistyson.welldone.data.remote.dto.AuthenticationResponse
import retrofit2.Response

class AuthenticationRepository() {

    private val authService = AuthenticationService.create()

    suspend fun signIn(authenticationRequest: AuthenticationRequest): Response<AuthenticationResponse> {
        return authService.signIn(authenticationRequest)
    }
}