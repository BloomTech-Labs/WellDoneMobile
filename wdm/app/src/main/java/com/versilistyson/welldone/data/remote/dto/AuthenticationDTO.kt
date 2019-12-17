package com.versilistyson.welldone.data.remote.dto

import com.squareup.moshi.Json
import com.versilistyson.welldone.data.remote.ResponseCode

data class AuthenticationResponse(
   val token: String,
    @Json(name = "status") val responseCode: String,
    @Json(name = "message") val responseMessage: String
)
data class AuthenticationRequest(
    @Json(name = "email_address") val email: String,
    @Json(name = "password") val password: String
)