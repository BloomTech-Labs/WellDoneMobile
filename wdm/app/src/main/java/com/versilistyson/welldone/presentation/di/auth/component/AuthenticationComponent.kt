package com.versilistyson.welldone.presentation.di.auth.component

import com.versilistyson.welldone.presentation.di.auth.module.AuthenticationModule
import com.versilistyson.welldone.presentation.di.auth.module.AuthViewModelModule
import com.versilistyson.welldone.presentation.di.auth.AuthActivityScope
import com.versilistyson.welldone.presentation.ui.authentication.AuthenticationActivity
import com.versilistyson.welldone.presentation.ui.authentication.SignInFragment
import com.versilistyson.welldone.presentation.ui.authentication.SplashScreenFragment
import dagger.Subcomponent

@AuthActivityScope
@Subcomponent(modules = [AuthViewModelModule::class, AuthenticationModule::class])
interface AuthenticationComponent {
    fun inject(activity: AuthenticationActivity)
    fun inject(fragment: SplashScreenFragment)
    fun inject(fragment: SignInFragment)
}