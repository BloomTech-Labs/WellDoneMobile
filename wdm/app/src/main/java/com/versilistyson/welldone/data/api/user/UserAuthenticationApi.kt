package com.versilistyson.welldone.data.api.user

import com.squareup.moshi.Json
import com.versilistyson.welldone.data.util.Mappable
import com.versilistyson.welldone.domain.framework.entity.Entity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthenticationApi {

    @POST("/api/operators/login")
    suspend fun login(
        @Body
        authenticationInfo: Dto.AuthenticationInfo
    ): Response<Dto.AuthenticationResponse>

    sealed class Dto {
        data class AuthenticationResponse(
            @Json(name = "token") val authToken: String
        ) : Dto(), Mappable<Entity.AuthenticationDetails> {
            override fun map(): Entity.AuthenticationDetails {
                return Entity.AuthenticationDetails(
                    token = authToken
                )
            }
        }

        data class AuthenticationInfo(
            @Json(name = "email_address") val email: String,
            @Json(name = "password") val password: String
        ): Dto()
    }
}