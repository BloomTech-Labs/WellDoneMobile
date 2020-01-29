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
    @ViewModelKey(FullScreenMapViewModel::class)
    abstract fun bindInitialMapViewModel(initialMapViewModel: InitialMapViewModel): ViewModel

    @DashboardActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(MapSharedViewModel::class)
    abstract fun bindInitialMapViewModel(mapSharedViewModel: MapSharedViewModel): ViewModel

    @DashboardActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(SensorDialogViewModel::class)
    abstract fun bindSensorDialogViewModel(sensorDialogViewModel: SensorDialogViewModel): ViewModel

    @DashboardActivityScope
    @Binds
    abstract fun bindViewModelProviderFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}