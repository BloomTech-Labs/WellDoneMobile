package com.versilistyson.welldone.util

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://well-done-staging.herokuapp.com/"

//fun createLogger(): HttpLoggingInterceptor {
//    val logger = HttpLoggingInterceptor()
//    logger.level = HttpLoggingInterceptor.Level.BASIC
//    logger.level = HttpLoggingInterceptor.Level.BODY
//
//    return logger
//}

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(object: Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "")
                .build()
            return chain.proceed(newRequest)
        }
    })
    .retryOnConnectionFailure(false)
    .readTimeout(30, TimeUnit.SECONDS)
    .connectTimeout(30, TimeUnit.SECONDS)
    .build()
