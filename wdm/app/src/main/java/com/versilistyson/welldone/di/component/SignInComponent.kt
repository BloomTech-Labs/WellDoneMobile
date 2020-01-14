package com.versilistyson.welldone.di.component

import com.versilistyson.welldone.di.module.NetworkModule
import com.versilistyson.welldone.ui.authentication.fragment.SignInFragment
import dagger.Subcomponent

@Subcomponent(modules = [NetworkModule::class])
interface SignInComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SignInComponent
    }

    fun inject(fragment: SignInFragment)
}