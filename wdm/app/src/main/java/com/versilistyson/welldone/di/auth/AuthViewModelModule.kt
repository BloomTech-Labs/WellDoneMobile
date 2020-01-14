package com.versilistyson.welldone.di.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.versilistyson.welldone.ViewModelKey
import com.versilistyson.welldone.ViewModelProviderFactory
import com.versilistyson.welldone.ui.authentication.AuthSharedViewModel
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