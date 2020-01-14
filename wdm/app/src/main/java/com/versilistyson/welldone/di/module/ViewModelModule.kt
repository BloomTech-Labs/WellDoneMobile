package com.versilistyson.welldone.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.versilistyson.welldone.ViewModelFactory
import com.versilistyson.welldone.ViewModelKey
import com.versilistyson.welldone.ui.authentication.AuthSharedViewModel
import com.versilistyson.welldone.ui.dashboard.DashboardViewmodel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthSharedViewModel::class)
    internal abstract fun authSharedViewModel(viewModel: AuthSharedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewmodel::class)
    internal abstract fun dashboardViewModel(viewModel: DashboardViewmodel): ViewModel
}