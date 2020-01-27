package com.versilistyson.welldone.data.api.user

import com.squareup.moshi.Json
import com.versilistyson.welldone.data.db.user.UserDetailsData
import com.versilistyson.welldone.data.util.Mappable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserDetailsApi {

    @GET("api/operators/op/info")
    suspend fun getUserDetails(): Response<Dto.UserDetails>

    @PUT("api/operators/op/info")
    suspend fun updateUserDetails(@Body userDetailsToUpdate: Dto.UserDetailsToUpdate): Response<Any>

    sealed class Dto {
        class UserDetails(
            @Json(name = "id") val userId: Long,
            @Json(name = "first_name") val firstName: String,
            @Json(name = "last_name") val lastName: String,
            @Json(name = "email_address") val emailAddress: String? = "",
            @Json(name = "mobile_number") val phoneNumber: String? = ""
        ) : Dto(), Mappable<UserDetailsData>{
            override fun map(): UserDetailsData =
                UserDetailsData(
                    userId = userId,
                    firstName = firstName,
                    lastName = lastName,
                    emailAddress = emailAddress,
                    phoneNumber = phoneNumber
                )
        }
        class UserDetailsToUpdate(
            @Json(name = "email_address") email: String,
            @Json(name = "mobile_number") phone: String,
            @Json(name = "password") password: String
        ): Dto()
    }
}