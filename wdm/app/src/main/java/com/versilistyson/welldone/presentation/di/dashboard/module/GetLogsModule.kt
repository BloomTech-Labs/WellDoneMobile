package com.versilistyson.welldone.presentation.di.dashboard.module

import com.versilistyson.welldone.data.datasource.log.LogLocalDataSourceImpl
import com.versilistyson.welldone.data.datasource.log.LogRemoteDataSourceImpl
import com.versilistyson.welldone.data.repository.LogRepositoryImpl
import com.versilistyson.welldone.domain.framework.datasource.log.LogLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.log.LogRemoteDataSource
import com.versilistyson.welldone.domain.framework.repository.LogRepository
import com.versilistyson.welldone.domain.framework.usecases.GetLogsUseCase
import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import dagger.Module
import dagger.Provides

@Module
abstract class GetLogsModule {

    @DashboardActivityScope
    @Provides
    abstract fun bindLogLocalDataSource(dataSource: LogLocalDataSourceImpl): LogLocalDataSource

    @DashboardActivityScope
    @Provides
    abstract fun bindLogRemoteDataSource(dataSource: LogRemoteDataSourceImpl): LogRemoteDataSource

    @DashboardActivityScope
    @Provides
    abstract fun bindLogRepository(logRepository: LogRepositoryImpl): LogRepository

    @DashboardActivityScope
    @Provides
    fun provideLogUseCase(logRepository: LogRepository) =
        GetLogsUseCase(logRepository)
}