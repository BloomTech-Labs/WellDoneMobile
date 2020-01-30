package com.versilistyson.welldone.presentation.di.app.component

import android.app.Application
import com.versilistyson.welldone.presentation.di.auth.component.AuthenticationComponent
import com.versilistyson.welldone.presentation.di.dashboard.component.DashboardComponent
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton


@InternalCoroutinesApi
@Singleton
@Component(modules = [AppComponent::class])
interface AppComponent {

    fun authComponent(): AuthenticationComponent
    fun dashboardComponent(): DashboardComponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
