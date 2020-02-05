package com.versilistyson.welldone.presentation.di.dashboard.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import com.versilistyson.welldone.presentation.viewmodel.*
import com.versilistyson.welldone.presentation.di.viewmodel_util.ViewModelKey
import com.versilistyson.welldone.presentation.di.viewmodel_util.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Module
abstract class DashboardViewModelModule {

    @DashboardActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(FullScreenMapViewModel::class)
    abstract fun bindFullScreenMapViewModel(fullScreenMapViewModel: FullScreenMapViewModel): ViewModel

    @DashboardActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(MainDashboardViewModel::class)
    abstract fun bindMainDashboardViewModel(mainDashboardViewModel: MainDashboardViewModel): ViewModel

    @DashboardActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(MapSharedViewModel::class)
    abstract fun bindMapSharedViewModel(mapSharedViewModel: MapSharedViewModel): ViewModel

    @DashboardActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(SensorDialogViewModel::class)
    abstract fun bindSensorDialogViewModel(sensorDialogViewModel: SensorDialogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LogDialogViewModel::class)
    abstract fun bindLogDialogViewModel(logDialogViewModel: LogDialogViewModel): ViewModel

    @DashboardActivityScope
    @Binds
    abstract fun bindViewModelProviderFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}