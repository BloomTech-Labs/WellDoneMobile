package com.versilistyson.welldone.presentation.di.auth.component

import com.versilistyson.welldone.presentation.di.auth.module.AuthenticationModule
import com.versilistyson.welldone.presentation.di.auth.module.AuthViewModelModule
import com.versilistyson.welldone.presentation.di.auth.AuthActivityScope
import com.versilistyson.welldone.presentation.di.auth.module.SignInModule
import com.versilistyson.welldone.presentation.ui.authentication.AuthenticationActivity
import com.versilistyson.welldone.presentation.ui.authentication.SignInFragment
import com.versilistyson.welldone.presentation.ui.authentication.SplashScreenFragment
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@AuthActivityScope
@Subcomponent(modules = [AuthViewModelModule::class, AuthenticationModule::class, SignInModule::class])
interface AuthenticationComponent {
    @InternalCoroutinesApi
    fun inject(activity: AuthenticationActivity)
    fun inject(fragment: SplashScreenFragment)
    fun inject(fragment: SignInFragment)
}