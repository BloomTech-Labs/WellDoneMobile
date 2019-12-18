package com.versilistyson.welldone.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.versilistyson.welldone.data.remote.dto.AuthenticationRequest
import com.versilistyson.welldone.data.remote.dto.AuthenticationResponse
import com.versilistyson.welldone.util.BASE_URL
import com.versilistyson.welldone.util.okHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST("/api/auth/login")
    suspend fun signIn(@Body authenticationRequest: AuthenticationRequest): Response<AuthenticationResponse>

    companion object Factory {
        fun create() : AuthenticationService {

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(AuthenticationService::class.java)
        }
    }
}