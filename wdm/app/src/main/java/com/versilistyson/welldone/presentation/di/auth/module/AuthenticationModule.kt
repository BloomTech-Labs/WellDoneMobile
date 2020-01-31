package com.versilistyson.welldone.presentation.di.auth.module

import com.versilistyson.welldone.data.api.user.UserAuthenticationApi
import com.versilistyson.welldone.presentation.di.auth.AuthActivityScope
import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AuthenticationModule {

    @AuthActivityScope
    @JvmStatic
    @Provides
    fun provideAuthenticationApi(retrofit: Retrofit): UserAuthenticationApi =
        retrofit.create(UserAuthenticationApi::class.java)
}