package com.versilistyson.welldone.data.remote.dto

import com.squareup.moshi.Json
import com.versilistyson.welldone.data.remote.ResponseCode

data class AuthenticationResponse(
    @Json(name = "access-control-allow-credentials") val allowCredentials: Boolean,
    @Json(name = "status") val responseCode: ResponseCode
)
data class AuthenticationRequest(
    @Json(name = "email_address") val email: String,
    @Json(name = "password") val password: String
)