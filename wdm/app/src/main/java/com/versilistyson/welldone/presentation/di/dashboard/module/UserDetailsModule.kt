package com.versilistyson.welldone.presentation.di.dashboard.module

import com.versilistyson.welldone.data.datasource.user.UserDetailsLocalDataSourceImpl
import com.versilistyson.welldone.data.datasource.user.UserDetailsRemoteDataSourceImpl
import com.versilistyson.welldone.data.repository.UserDetailRepositoryImpl
import com.versilistyson.welldone.domain.framework.datasource.user.UserDetailsLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.user.UserDetailsRemoteDataSource
import com.versilistyson.welldone.domain.framework.repository.UserDetailRepository
import com.versilistyson.welldone.domain.framework.usecases.user.GetUserDetailsUseCase
import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@Module
abstract class UserDetailsModule {

    @DashboardActivityScope
    @Binds
    abstract fun bindUserDetailsLocalDataSource(datasource: UserDetailsLocalDataSourceImpl): UserDetailsLocalDataSource

    @DashboardActivityScope
    @Binds
    abstract fun bindUserDetailsRemoteDataSource(dataSource: UserDetailsRemoteDataSourceImpl): UserDetailsRemoteDataSource

    @DashboardActivityScope
    @Binds
    abstract fun bindUserDetailsRepository(userDetailsRepository: UserDetailRepositoryImpl): UserDetailRepository
}