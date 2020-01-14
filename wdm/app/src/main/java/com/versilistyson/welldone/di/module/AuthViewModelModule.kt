package com.versilistyson.welldone.di.module

import androidx.lifecycle.ViewModel
import com.versilistyson.welldone.ViewModelKey
import com.versilistyson.welldone.ui.authentication.AuthSharedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthSharedViewModel::class)
    abstract fun bindAuthViewModel(authSharedViewModel: AuthSharedViewModel): ViewModel
}