package com.versilistyson.welldone.di.module

import com.versilistyson.welldone.data.remote.WellDoneApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
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
    fun createLogger(): Interceptor {
        return object: Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "")
                    .build()
                return chain.proceed(newRequest)
            }
        }
    }

    @Singleton
    @JvmStatic
    @Provides
    fun createOkHttpClient(logger: Interceptor){
        OkHttpClient.Builder()
            .addInterceptor(logger)
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