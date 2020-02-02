package com.versilistyson.welldone.presentation.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.versilistyson.welldone.R
import com.versilistyson.welldone.presentation.MyApplication
import com.versilistyson.welldone.presentation.di.dashboard.component.DashboardComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@FlowPreview
@InternalCoroutinesApi
class DashboardActivity : AppCompatActivity() {

    lateinit var dashboardComponent: DashboardComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dashboardComponent = (applicationContext as MyApplication).appComponent.dashboardComponent()
        dashboardComponent.inject(this)
    }
}
