package com.versilistyson.welldone.data.remote


import com.versilistyson.welldone.data.remote.dto.AuthenticationRequest
import com.versilistyson.welldone.data.remote.dto.AuthenticationResponse
import com.versilistyson.welldone.util.BASE_URL
import com.versilistyson.welldone.util.okHttpClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST("/api/auth/login")
    suspend fun authenticate(@Body authenticationRequest: AuthenticationRequest): AuthenticationResponse

    companion object Factory {
        fun create() : AuthenticationService {

            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(AuthenticationService::class.java)
        }
    }
}