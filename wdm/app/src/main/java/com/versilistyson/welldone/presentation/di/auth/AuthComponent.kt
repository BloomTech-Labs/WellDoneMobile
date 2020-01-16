package com.versilistyson.welldone.presentation.di.auth

import com.versilistyson.welldone.presentation.ui.authentication.AuthenticationActivity
import com.versilistyson.welldone.presentation.ui.authentication.SignInFragment
import com.versilistyson.welldone.presentation.ui.authentication.SplashScreenFragment
import dagger.Subcomponent

@AuthActivityScope
@Subcomponent(modules = [AuthViewModelModule::class, AuthNetworkModule::class])
interface AuthComponent {

    fun inject(activity: AuthenticationActivity)
    fun inject(fragment: SplashScreenFragment)
    fun inject(fragment: SignInFragment)
}