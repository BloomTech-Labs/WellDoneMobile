package com.versilistyson.welldone.data.api

import com.squareup.moshi.Json
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthenticationApi {
    @POST("/api/auth/login")
    suspend fun signIn(
        @Body
        @Json(name = "email_address") email: String,
        @Json(name = "password") password: String
    ): Response<Dto.AuthenticationResponse>

    sealed class Dto {
        data class AuthenticationResponse(
            @Json(name = "token") val authToken: String
        ) : Dto()
    }
}