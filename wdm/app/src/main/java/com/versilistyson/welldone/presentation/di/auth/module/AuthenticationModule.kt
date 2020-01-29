package com.versilistyson.welldone.presentation.di.auth.module

import com.versilistyson.welldone.data.api.user.UserAuthenticationApi
import com.versilistyson.welldone.presentation.di.auth.AuthActivityScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class AuthenticationModule {

    @AuthActivityScope
    @Provides
    fun provideAuthenticationApi(retrofit: Retrofit): UserAuthenticationApi =
        retrofit.create(UserAuthenticationApi::class.java)
}