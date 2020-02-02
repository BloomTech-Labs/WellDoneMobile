package com.versilistyson.welldone.presentation.di.dashboard.module

import com.versilistyson.welldone.data.datasource.sensor.SensorLocalDataSourceImpl
import com.versilistyson.welldone.data.datasource.sensor.SensorRemoteDataSourceImpl
import com.versilistyson.welldone.data.repository.SensorRepositoryImpl
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import com.versilistyson.welldone.domain.framework.usecases.sensor.GetCacheSensorStreamUseCase
import com.versilistyson.welldone.domain.framework.usecases.sensor.GetFreshSensorStreamUseCase
import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
abstract class SensorModule {

    @DashboardActivityScope
    @Binds
    abstract fun bindSensorLocalDataSource(dataSource: SensorLocalDataSourceImpl): SensorLocalDataSource

    @DashboardActivityScope
    @Binds
    abstract fun bindSensorRemoteDataSource(dataSourceImpl: SensorRemoteDataSourceImpl): SensorRemoteDataSource

    @DashboardActivityScope
    @Binds
    abstract fun bindSensorRepository(sensorRepositoryImpl: SensorRepositoryImpl): SensorRepository
}