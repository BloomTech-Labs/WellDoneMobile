package com.versilistyson.welldone.util

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

const val BASE_URL = "virtserver.swaggerhub.com/Jessiehongtran/well-done/1.0.0"

fun createLogger(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BASIC
    logger.level = HttpLoggingInterceptor.Level.BODY

    return logger
}

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(createLogger())
    .retryOnConnectionFailure(false)
    .readTimeout(30, TimeUnit.SECONDS)
    .connectTimeout(30, TimeUnit.SECONDS)
    .build()
