package com.versilistyson.welldone.presentation.di.app.component

import android.app.Application
import com.versilistyson.welldone.presentation.di.app.module.AppModule
import com.versilistyson.welldone.presentation.di.app.module.RoomModule
import com.versilistyson.welldone.presentation.di.auth.component.AuthenticationComponent
import com.versilistyson.welldone.presentation.di.dashboard.component.DashboardComponent
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@InternalCoroutinesApi
@Singleton
@Component(modules = [AppModule::class, RoomModule::class])
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