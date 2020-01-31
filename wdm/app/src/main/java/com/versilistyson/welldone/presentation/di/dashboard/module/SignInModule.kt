package com.versilistyson.welldone.presentation.di.dashboard.module

import com.versilistyson.welldone.data.datasource.user.AuthenticationLocalDataSourceImpl
import com.versilistyson.welldone.data.datasource.user.AuthenticationRemoteDataSourceImpl
import com.versilistyson.welldone.data.repository.AuthenticationRepositoryImpl
import com.versilistyson.welldone.domain.framework.datasource.user.AuthenticationLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.user.AuthenticationRemoteDataSource
import com.versilistyson.welldone.domain.framework.repository.AuthenticationRepository
import com.versilistyson.welldone.domain.framework.usecases.user.SignInUseCase
import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class SignInModule {

    @DashboardActivityScope
    @Binds
    abstract fun bindAuthenticationLocalDataSource(datasource: AuthenticationLocalDataSourceImpl): AuthenticationLocalDataSource

    @DashboardActivityScope
    @Binds
    abstract fun bindAuthenticationRemoteDataSource(datasource: AuthenticationRemoteDataSourceImpl): AuthenticationRemoteDataSource

    @DashboardActivityScope
    @Binds
    abstract fun bindAuthenticationRepository(repository: AuthenticationRepositoryImpl): AuthenticationRepository
}