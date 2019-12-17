package com.versilistyson.welldone.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.versilistyson.welldone.data.remote.dto.SensorRecentResponse
import com.versilistyson.welldone.repository.DashboardRepository

class DashboardViewmodel: ViewModel() {

    private val dashboardRepository = DashboardRepository()

    val sensorLiveData = liveData {
        emit(dashboardRepository.fetchAllSensors())
    }

    private val _sensorStatusLiveData: MutableLiveData<MutableList<SensorRecentResponse>> by lazy{
        MutableLiveData<MutableList<SensorRecentResponse>>()
    }

    val sensorStatusLiveData : LiveData<MutableList<SensorRecentResponse>>
    get() = _sensorStatusLiveData

    fun addSensorStatus(sensorRecentResponse: SensorRecentResponse){
        sensorStatusLiveData.value?.let{
            it.add(sensorRecentResponse)
        }
    }
}