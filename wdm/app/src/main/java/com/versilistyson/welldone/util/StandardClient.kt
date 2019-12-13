package com.versilistyson.welldone.util

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class StandardClient<T>(baseUrl: String) {

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun create(service: Class<T>): T {
        return retrofit.create(service)
    }
}