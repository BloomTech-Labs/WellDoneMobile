package com.versilistyson.welldone.presentation.di.app.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.versilistyson.welldone.data.api.user.UserAuthenticationApi
import com.versilistyson.welldone.presentation.di.auth.AuthActivityScope
import com.versilistyson.welldone.presentation.util.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

object AppModule {

    @Singleton
    @JvmStatic
    @Provides
    fun moshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @JvmStatic
    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)

    @Singleton
    @JvmStatic
    @Provides
    fun provideRetrofit(okHttpClientBuilder: OkHttpClient.Builder, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

}