package com.versilistyson.welldone.presentation.di.auth.module

import com.versilistyson.welldone.data.datasource.user.AuthenticationLocalDataSourceImpl
import com.versilistyson.welldone.data.datasource.user.AuthenticationRemoteDataSourceImpl
import com.versilistyson.welldone.data.repository.AuthenticationRepositoryImpl
import com.versilistyson.welldone.domain.framework.datasource.user.AuthenticationLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.user.AuthenticationRemoteDataSource
import com.versilistyson.welldone.domain.framework.repository.AuthenticationRepository
import com.versilistyson.welldone.presentation.di.auth.AuthActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class SignInModule {

    @AuthActivityScope
    @Binds
    abstract fun bindAuthenticationLocalDataSource(datasource: AuthenticationLocalDataSourceImpl): AuthenticationLocalDataSource

    @AuthActivityScope
    @Binds
    abstract fun bindAuthenticationRemoteDataSource(datasource: AuthenticationRemoteDataSourceImpl): AuthenticationRemoteDataSource

    @AuthActivityScope
    @Binds
    abstract fun bindAuthenticationRepository(authRepo: AuthenticationRepositoryImpl): AuthenticationRepository
}