package com.versilistyson.welldone.presentation.di.dashboard.module

import com.versilistyson.welldone.data.api.LogApi
import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.api.user.UserAuthenticationApi
import com.versilistyson.welldone.data.api.user.UserDetailsApi
import com.versilistyson.welldone.data.db.WellDoneDatabase
import com.versilistyson.welldone.data.db.log.LogDao
import com.versilistyson.welldone.data.db.user.UserDetailsDao
import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.create

@Module
class DashboardModule {

    @DashboardActivityScope
    @Provides
    fun provideInterceptor(token: String): Interceptor =
        object: Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", token)
                    .build()
                return chain.proceed(request)
            }
        }

    @DashboardActivityScope
    @Provides
    fun provideOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder, interceptor: Interceptor): OkHttpClient.Builder =
        okHttpClientBuilder.addInterceptor(interceptor)

    @DashboardActivityScope
    @Provides
    fun provideUserAuthenticationApi(retrofit: Retrofit): UserAuthenticationApi =
        retrofit.create(UserAuthenticationApi::class.java)

    @DashboardActivityScope
    @Provides
    fun provideUserDetailsApi(retrofit: Retrofit): UserDetailsApi =
        retrofit.create(UserDetailsApi::class.java)

    @DashboardActivityScope
    @Provides
    fun provideUserDetailsDao(wellDoneDatabase: WellDoneDatabase): UserDetailsDao =
        wellDoneDatabase.userDao()

    @DashboardActivityScope
    @Provides
    fun provideSensorApi(retrofit: Retrofit): SensorApi =
        retrofit.create(SensorApi::class.java)

    @DashboardActivityScope
    @Provides
    fun provideSensorDao(wellDoneDatabase: WellDoneDatabase) =
        wellDoneDatabase.sensorDao()

    @DashboardActivityScope
    @Provides
    fun provideLogApi(retrofit: Retrofit): LogApi =
        retrofit.create(LogApi::class.java)

    @DashboardActivityScope
    @Provides
    fun provideLogDao(wellDoneDatabase: WellDoneDatabase): LogDao =
        wellDoneDatabase.logDao()

}