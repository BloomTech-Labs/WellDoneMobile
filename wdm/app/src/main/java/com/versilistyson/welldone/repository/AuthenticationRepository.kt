package com.versilistyson.welldone.repository

import com.versilistyson.welldone.data.remote.AuthenticationService
import com.versilistyson.welldone.data.remote.dto.AuthenticationRequest
import com.versilistyson.welldone.data.remote.dto.AuthenticationResponse

class AuthenticationRepository() {

    private val authService = AuthenticationService.create()

    suspend fun authenticateUser(authenticationRequest: AuthenticationRequest) : AuthenticationResponse {
        return authService.authenticate(authenticationRequest)
    }
}