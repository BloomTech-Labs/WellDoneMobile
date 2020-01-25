package com.versilistyson.welldone.presentation.di.app

import android.app.Application
import com.versilistyson.welldone.presentation.di.auth.AuthComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/*
@Singleton
@Component(
    modules = [AppModule::class,
               ViewModelFactoryModule::class]
)
interface AppComponent {

    fun authComponent(): AuthComponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}*/
