package com.versilistyson.welldone.di.auth

import com.versilistyson.welldone.ui.authentication.AuthenticationActivity
import com.versilistyson.welldone.ui.authentication.fragment.SignInFragment
import com.versilistyson.welldone.ui.authentication.fragment.SplashScreenFragment
import dagger.Subcomponent

@AuthActivityScope
@Subcomponent(modules = [AuthViewModelModule::class, AuthNetworkModule::class])
interface AuthComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(authViewModelModule: AuthViewModelModule, authNetworkModule: AuthNetworkModule): AuthComponent
    }

    fun inject(activity: AuthenticationActivity)
    fun inject(fragment: SplashScreenFragment)
    fun inject(fragment: SignInFragment)
}