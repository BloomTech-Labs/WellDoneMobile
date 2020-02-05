package com.versilistyson.welldone.presentation.di.dashboard.module

import com.versilistyson.welldone.data.api.log.LogApi
import com.versilistyson.welldone.data.api.log.LogImageApi
import com.versilistyson.welldone.data.api.sensor.SensorApi
import com.versilistyson.welldone.data.api.user.UserDetailsApi
import com.versilistyson.welldone.data.db.WellDoneDatabase
import com.versilistyson.welldone.data.db.log.LogDao
import com.versilistyson.welldone.data.db.log.LogImageDao
import com.versilistyson.welldone.data.db.user.UserDetailsDao
import com.versilistyson.welldone.domain.framework.repository.*
import com.versilistyson.welldone.domain.framework.usecases.log.AddImageToLogUseCase
import com.versilistyson.welldone.domain.framework.usecases.log.GetLogImagesUseCase
import com.versilistyson.welldone.domain.framework.usecases.log.GetLogsUseCase
import com.versilistyson.welldone.domain.framework.usecases.sensor.GetCacheSensorStreamUseCase
import com.versilistyson.welldone.domain.framework.usecases.sensor.GetFreshSensorStreamUseCase
import com.versilistyson.welldone.domain.framework.usecases.user.GetUserDetailsUseCase
import com.versilistyson.welldone.domain.framework.usecases.user.SignInUseCase
import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.InternalCoroutinesApi
import retrofit2.Retrofit

@InternalCoroutinesApi
@Module
class DashboardModule {

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
    fun provideLogImageApi(retrofit: Retrofit): LogImageApi =
        retrofit.create(LogImageApi::class.java)

    @DashboardActivityScope
    @Provides
    fun provideLogDao(wellDoneDatabase: WellDoneDatabase): LogDao =
        wellDoneDatabase.logDao()

    @DashboardActivityScope
    @Provides
    fun provideLogImageDao(wellDoneDatabase: WellDoneDatabase): LogImageDao =
        wellDoneDatabase.logImageDao()

    @DashboardActivityScope
    @Provides
    fun provideUserDetailsUseCase(userDetailsRepository: UserDetailRepository) =
        GetUserDetailsUseCase(userDetailsRepository)

    @DashboardActivityScope
    @Provides
    fun provideLogUseCase(logRepository: LogRepository) =
        GetLogsUseCase(logRepository)

    @DashboardActivityScope
    @Provides
    fun provideLogImageUseCase(logImageRepository: LogImageRepository) =
        GetLogImagesUseCase(logImageRepository)

    @DashboardActivityScope
    @Provides
    fun provideAddImageToLogUseCase(logImageRepository: LogImageRepository) =
        AddImageToLogUseCase(logImageRepository)

    @DashboardActivityScope
    @Provides
    fun provideSignInUseCase(repository: AuthenticationRepository): SignInUseCase =
        SignInUseCase(repository)

    @DashboardActivityScope
    @Provides
    fun provideFreshSensorUseCase(sensorRepository: SensorRepository): GetFreshSensorStreamUseCase {
        return GetFreshSensorStreamUseCase(sensorRepository)
    }

    @DashboardActivityScope
    @Provides
    fun provideCacheSensorUseCase(sensorRepository: SensorRepository): GetCacheSensorStreamUseCase {
        return GetCacheSensorStreamUseCase(sensorRepository)
    }
}