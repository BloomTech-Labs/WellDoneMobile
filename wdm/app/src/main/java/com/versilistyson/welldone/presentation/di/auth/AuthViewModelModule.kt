package com.versilistyson.welldone.presentation.di.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.versilistyson.welldone.presentation.viewmodel.ViewModelKey
import com.versilistyson.welldone.presentation.viewmodel.ViewModelProviderFactory
import com.versilistyson.welldone.presentation.viewmodel.AuthSharedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @AuthActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(AuthSharedViewModel::class)
    abstract fun bindAuthViewModel(authSharedViewModel: AuthSharedViewModel): ViewModel

    @AuthActivityScope
    @Binds
    abstract fun bindViewModelProviderFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}