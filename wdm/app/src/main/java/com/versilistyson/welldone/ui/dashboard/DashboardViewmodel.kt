package com.versilistyson.welldone.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.versilistyson.welldone.repository.DashboardRepository

class DashboardViewmodel: ViewModel() {

    private val dashboardRepository = DashboardRepository()

    val sensorLiveData = liveData {
        emit(dashboardRepository.fetchAllSensors())
    }
}