package com.versilistyson.welldone.presentation.di.auth

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.versilistyson.welldone.data.api.user.UserAuthenticationApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
object AuthNetworkModule {

    val BASE_URL = "https://well-done-staging.herokuapp.com/"

    @AuthActivityScope
    @JvmStatic
    @Provides
    fun moshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @AuthActivityScope
    @JvmStatic
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    @AuthActivityScope
    @JvmStatic
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @AuthActivityScope
    @JvmStatic
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): UserAuthenticationApi =
        retrofit.create(UserAuthenticationApi::class.java)
}