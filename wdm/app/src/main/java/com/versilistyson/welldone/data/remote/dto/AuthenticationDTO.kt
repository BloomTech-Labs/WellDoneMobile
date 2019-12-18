package com.versilistyson.welldone.data.remote.dto

import com.squareup.moshi.Json
import com.versilistyson.welldone.data.remote.ResponseCode

data class AuthenticationResponse(
   @Json(name = "token") val authToken: String,
   @Json(name = "id") val userId: Int
)
data class AuthenticationRequest(
    @Json(name = "email_address") val email: String,
    @Json(name = "password") val password: String
)