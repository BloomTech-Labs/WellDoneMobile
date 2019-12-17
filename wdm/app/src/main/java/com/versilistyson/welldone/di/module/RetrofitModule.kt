package com.versilistyson.welldone.di.module

import com.versilistyson.welldone.data.remote.WellDoneApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object RetrofitModule {

    val BASE_URL = "https://well-done-staging.herokuapp.com/"

    @Singleton
    @JvmStatic
    @Provides
    fun createLogger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        logger.level = HttpLoggingInterceptor.Level.BODY

        return logger
    }

    @Singleton
    @JvmStatic
    @Provides
    fun createOkHttpClient(logger: HttpLoggingInterceptor){
        OkHttpClient.Builder()
            .addInterceptor(com.versilistyson.welldone.util.createLogger())
            .retryOnConnectionFailure(false)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @JvmStatic
    @Provides
    fun retrofitObject(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @JvmStatic
    @Provides
    fun wellDoneRetrofitService(retrofit: Retrofit): WellDoneApi {
        return retrofit.create(WellDoneApi::class.java)
    }

}