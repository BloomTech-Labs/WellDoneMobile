package com.versilistyson.welldone.di

import android.app.Application
import android.content.Context
import com.versilistyson.welldone.MyApplication
import com.versilistyson.welldone.di.auth.AuthComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

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
}