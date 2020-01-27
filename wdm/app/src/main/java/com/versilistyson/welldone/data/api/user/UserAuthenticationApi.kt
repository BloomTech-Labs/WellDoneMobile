package com.versilistyson.welldone.data.api.user

import com.squareup.moshi.Json
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthenticationApi {

    @POST("/api/operators/login")
    suspend fun login(
        @Body
        @Json(name = "email_address") email: String,
        @Json(name = "password") password: String
    ): Response<Dto.AuthenticationResponse>

    sealed class Dto {
        data class AuthenticationResponse(
            @Json(name = "token") val authToken: String,
            @Json(name = "user_id") val userId: Long
        ) : Dto()
    }
}