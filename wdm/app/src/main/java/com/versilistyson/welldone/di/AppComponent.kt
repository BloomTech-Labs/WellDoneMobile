package com.versilistyson.welldone.di

import android.content.Context
import com.versilistyson.welldone.di.auth.AuthComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
               ViewModelFactoryModule::class,
               SubComponentsModule::class])

interface AppComponent {

    fun authComponent(): AuthComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}