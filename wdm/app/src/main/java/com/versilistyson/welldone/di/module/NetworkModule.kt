package com.versilistyson.welldone.di.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.versilistyson.welldone.data.remote.WellDoneApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
@Module
object NetworkModule {

    val BASE_URL = "https://well-done-staging.herokuapp.com/"

    @Singleton
    @JvmStatic
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @JvmStatic
    @Provides
    fun provideLogger(): Interceptor =
        object: Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "")
                    .build()
                return chain.proceed(newRequest)
            }
        }

    @Singleton
    @JvmStatic
    @Provides
    fun provideOkHttpClient(logger: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logger)
            .retryOnConnectionFailure(false)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @JvmStatic
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): WellDoneApi =
        retrofit.create(WellDoneApi::class.java)
}