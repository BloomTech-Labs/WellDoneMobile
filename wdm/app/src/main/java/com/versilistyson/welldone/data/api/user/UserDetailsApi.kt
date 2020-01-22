package com.versilistyson.welldone.data.api.user

import com.squareup.moshi.Json
import retrofit2.Response
import retrofit2.http.GET

interface UserDetailsApi {
    @GET("TODO()")
    suspend fun getUserDetails(): Response<Dto.UserDetails>
    sealed class Dto {
        data class UserDetails(
            @Json(name = "id") val id: String,
            @Json(name = "first_name") val firstName: String,
            @Json(name = "last_name") val lastName: String,
            @Json(name = "email") val email: String? = "",
            @Json(name = "phone") val phone: String? = ""
        ) : Dto()
    }
}