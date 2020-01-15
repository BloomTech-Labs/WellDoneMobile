package com.versilistyson.welldone.data.api

import com.squareup.moshi.Json
import com.versilistyson.welldone.data.remote.dto.AuthenticationRequest
import com.versilistyson.welldone.data.remote.dto.AuthenticationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/api/auth/login")
    suspend fun signIn(
        @Body
        @Json(name = "email_address") email: String,
        @Json(name = "password") password: String
    ): Response<AuthenticationResponse>

    sealed class Dto {
        data class AuthenticationResponse(
            @Json(name = "token") val authToken: String,
            @Json(name = "id") val userId: Int
        ) : Dto()
    }
}