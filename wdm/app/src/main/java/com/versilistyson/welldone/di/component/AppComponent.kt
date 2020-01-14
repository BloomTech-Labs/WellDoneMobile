package com.versilistyson.welldone.di.component

import com.versilistyson.welldone.di.module.NetworkModule
import com.versilistyson.welldone.di.module.SubComponentsModule
import com.versilistyson.welldone.di.module.ViewModelFactoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelFactoryModule::class, SubComponentsModule::class])
interface AppComponent {
    fun authComponent(): AuthComponent.Factory
}