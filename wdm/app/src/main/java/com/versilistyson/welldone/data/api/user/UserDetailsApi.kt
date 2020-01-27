package com.versilistyson.welldone.data.api.user

import com.squareup.moshi.Json
import com.versilistyson.welldone.data.db.user.UserDetailsData
import com.versilistyson.welldone.data.util.Mappable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserDetailsApi {

    @GET()
    suspend fun getUserDetails(): Response<Dto.UserDetails>

    @PUT("")
    suspend fun updateUserDetails(): Response<Any>

    sealed class Dto {
        class UserDetails(
            @Json(name = "id") val userId: Long,
            @Json(name = "first_name") val firstName: String,
            @Json(name = "last_name") val lastName: String,
            @Json(name = "email_address") val email: String? = "",
            @Json(name = "phone") val phone: String? = ""
        ) : Dto(), Mappable<UserDetailsData>{
            override fun map(): UserDetailsData =
                UserDetailsData(
                    id = userId,
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    phone = phone
                )
        }
        class UserDetailsToUpdate(
            @Json(name = "email_address") email: String,
            @Json(name = "phone") phone: String
        ): Dto()
    }
}