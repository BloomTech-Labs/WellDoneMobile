package com.versilistyson.welldone.presentation.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.versilistyson.welldone.R
import com.versilistyson.welldone.presentation.viewmodel.MapSharedViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
class DashboardActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mapSharedViewModel: MapSharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        mapSharedViewModel = viewModelFactory.create(MapSharedViewModel::class.java)
        lifecycleScope.launchWhenStarted {

        }
        lifecycleScope.launchWhenCreated {

        }
    }
}
