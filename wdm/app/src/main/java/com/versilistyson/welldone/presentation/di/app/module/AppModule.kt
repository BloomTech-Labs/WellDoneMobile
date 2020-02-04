package com.versilistyson.welldone.presentation.di.app.module

import android.app.Application
import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.versilistyson.welldone.data.util.SharedPreferenceKeys
import com.versilistyson.welldone.presentation.util.BASE_URL
import com.versilistyson.welldone.presentation.util.SharedPreference
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun getSharedPreferences(application: Application): SharedPreference =
        SharedPreference(application)

    @Singleton
    @Provides
    fun moshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    fun provideOkHttpClientBuilder(application: Application): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)

        val token = application.getSharedPreferences(SharedPreferenceKeys.Authentication.KEY, Context.MODE_PRIVATE).getString(
                SharedPreferenceKeys.Authentication.USER_TOKEN, null
            )
        if(token != null){
            val interceptor = object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", application.getSharedPreferences(
                        SharedPreferenceKeys.Authentication.KEY, Context.MODE_PRIVATE).getString(
                        SharedPreferenceKeys.Authentication.USER_TOKEN, null)!!)
                    .build()
                return chain.proceed(request)
                }
            }
            builder.addInterceptor(interceptor)
        }
        return builder
    }

    @Provides
    fun provideRetrofit(okHttpClientBuilder: OkHttpClient.Builder, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}