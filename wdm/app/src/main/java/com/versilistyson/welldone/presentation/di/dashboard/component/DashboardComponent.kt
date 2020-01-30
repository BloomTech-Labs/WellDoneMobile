package com.versilistyson.welldone.presentation.di.dashboard.component

import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import com.versilistyson.welldone.presentation.di.dashboard.module.*
import com.versilistyson.welldone.presentation.ui.dashboard.DashboardActivity
import com.versilistyson.welldone.presentation.ui.dashboard.MainDashboardFragment
import com.versilistyson.welldone.presentation.ui.dashboard.detail.LogDialogFragment
import com.versilistyson.welldone.presentation.ui.dashboard.detail.PumpDialogDetailFragment
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@DashboardActivityScope
@Subcomponent(modules = [DashboardViewModelModule::class, DashboardModule::class,
    GetSensorsModule::class, GetUserDetailsModule::class, GetLogsModule::class, SignInModule::class])
interface DashboardComponent {
    fun inject(activity: DashboardActivity)
    fun inject(fragment: MainDashboardFragment)
    fun inject(fragment: LogDialogFragment)
    fun inject(fragment: PumpDialogDetailFragment)
}