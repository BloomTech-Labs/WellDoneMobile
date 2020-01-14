package com.versilistyson.welldone.di.auth

import com.versilistyson.welldone.di.AppComponent
import com.versilistyson.welldone.ui.authentication.AuthenticationActivity
import com.versilistyson.welldone.ui.authentication.fragment.SignInFragment
import com.versilistyson.welldone.ui.authentication.fragment.SplashScreenFragment
import dagger.BindsInstance
import dagger.Subcomponent

@AuthActivityScope
@Subcomponent(modules = [AuthViewModelModule::class, AuthNetworkModule::class])
interface AuthComponent {

    fun inject(activity: AuthenticationActivity)
    fun inject(fragment: SplashScreenFragment)
    fun inject(fragment: SignInFragment)
}