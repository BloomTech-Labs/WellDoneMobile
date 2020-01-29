package com.versilistyson.welldone.presentation.di.dashboard.component

import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import com.versilistyson.welldone.presentation.di.dashboard.module.DashboardViewModelModule
import com.versilistyson.welldone.presentation.ui.dashboard.DashboardActivity
import dagger.Subcomponent

@DashboardActivityScope
@Subcomponent(modules = [DashboardViewModelModule::class])
interface DashboardComponent {
    fun inject(activity: DashboardActivity)
}