package com.versilistyson.welldone.presentation.di.dashboard.module

import com.versilistyson.welldone.data.datasource.log.LogImageLocalDataSourceImpl
import com.versilistyson.welldone.data.datasource.log.LogImageRemoteDataSourceImpl
import com.versilistyson.welldone.data.datasource.log.LogLocalDataSourceImpl
import com.versilistyson.welldone.data.datasource.log.LogRemoteDataSourceImpl
import com.versilistyson.welldone.data.repository.LogImageRepositoryImpl
import com.versilistyson.welldone.data.repository.LogRepositoryImpl
import com.versilistyson.welldone.domain.framework.datasource.log.LogImageLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.log.LogImageRemoteDataSource
import com.versilistyson.welldone.domain.framework.datasource.log.LogLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.log.LogRemoteDataSource
import com.versilistyson.welldone.domain.framework.repository.LogImageRepository
import com.versilistyson.welldone.domain.framework.repository.LogRepository
import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@Module
abstract class LogsModule {

    @DashboardActivityScope
    @Binds
    abstract fun bindLogImageLocalDataSource(dataSource: LogImageLocalDataSourceImpl): LogImageLocalDataSource

    @DashboardActivityScope
    @Binds
    abstract fun bindLogImageRemoteDataSource(dataSource: LogImageRemoteDataSourceImpl): LogImageRemoteDataSource

    @DashboardActivityScope
    @Binds
    abstract fun bindLogLocalDataSource(dataSource: LogLocalDataSourceImpl): LogLocalDataSource

    @DashboardActivityScope
    @Binds
    abstract fun bindLogRemoteDataSource(dataSource: LogRemoteDataSourceImpl): LogRemoteDataSource

    @DashboardActivityScope
    @Binds
    abstract fun bindLogRepository(logRepository: LogRepositoryImpl): LogRepository

    @DashboardActivityScope
    @Binds
    abstract fun bindLogImageRepository(logImageRepository: LogImageRepositoryImpl): LogImageRepository
}