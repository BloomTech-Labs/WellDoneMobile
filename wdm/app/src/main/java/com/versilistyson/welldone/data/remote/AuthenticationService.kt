package com.versilistyson.welldone.data.remote

import com.versilistyson.welldone.data.remote.dto.AuthenticationRequest
import com.versilistyson.welldone.data.remote.dto.AuthenticationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST("/api/auth/login")
    suspend fun signIn(@Body authenticationRequest: AuthenticationRequest): Response<AuthenticationResponse>
}