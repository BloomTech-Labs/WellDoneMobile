package com.versilistyson.welldone.presentation.di

import androidx.lifecycle.ViewModelProvider
import com.versilistyson.welldone.presentation.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}