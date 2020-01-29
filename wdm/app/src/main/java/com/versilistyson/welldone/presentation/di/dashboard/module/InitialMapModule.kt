package com.versilistyson.welldone.presentation.di.dashboard.module

import com.versilistyson.welldone.data.datasource.sensor.SensorLocalDataSourceImpl
import com.versilistyson.welldone.data.datasource.sensor.SensorRemoteDataSourceImpl
import com.versilistyson.welldone.data.repository.SensorRepositoryImpl
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import com.versilistyson.welldone.domain.framework.usecases.GetSensorsUseCase
import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
abstract class InitialMapModule {

    @DashboardActivityScope
    @Provides
    abstract fun bindSensorLocalDataSource(dataSource: SensorLocalDataSourceImpl): SensorLocalDataSource

    @DashboardActivityScope
    @Provides
    abstract fun bindSensorRemoteDataSource(dataSourceImpl: SensorRemoteDataSourceImpl): SensorRemoteDataSource

    @DashboardActivityScope
    @Provides
    abstract fun provideSensorRepository(sensorRepositoryImpl: SensorRepositoryImpl): SensorRepository

    @DashboardActivityScope
    @Provides
    fun provideSensorUseCase(sensorRepository: SensorRepository, coroutineScope: CoroutineScope): GetSensorsUseCase {
        return GetSensorsUseCase(sensorRepository, coroutineScope)
    }
}