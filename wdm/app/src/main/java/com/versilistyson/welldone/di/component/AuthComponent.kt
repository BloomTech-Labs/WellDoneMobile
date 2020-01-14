package com.versilistyson.welldone.di.component

import com.versilistyson.welldone.di.module.AuthViewModelModule
import com.versilistyson.welldone.di.scopes.AuthActivityScope
import com.versilistyson.welldone.ui.authentication.AuthenticationActivity
import com.versilistyson.welldone.ui.authentication.fragment.SignInFragment
import com.versilistyson.welldone.ui.authentication.fragment.SplashScreenFragment
import dagger.Subcomponent

@AuthActivityScope
@Subcomponent(modules = [AuthViewModelModule::class])
interface AuthComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthComponent
    }

    fun inject(activity: AuthenticationActivity)
    fun inject(fragment: SplashScreenFragment)
    fun inject(fragment: SignInFragment)
}