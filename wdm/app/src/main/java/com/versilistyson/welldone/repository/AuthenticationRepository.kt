package com.versilistyson.welldone.repository

import com.versilistyson.welldone.data.remote.AuthenticationService
import com.versilistyson.welldone.data.remote.dto.AuthenticationRequest
import com.versilistyson.welldone.data.remote.dto.AuthenticationResponse
import retrofit2.Response
import javax.inject.Inject

class AuthenticationRepository @Inject constructor (private val authService: AuthenticationService) {

    suspend fun signIn(authenticationRequest: AuthenticationRequest): Response<AuthenticationResponse> {
        return authService.signIn(authenticationRequest)
    }
}