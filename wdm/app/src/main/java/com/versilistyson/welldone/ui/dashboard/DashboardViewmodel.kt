package com.versilistyson.welldone.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.google.android.gms.maps.model.LatLng
import com.versilistyson.welldone.data.remote.dto.SensorRecentResponse
import com.versilistyson.welldone.repository.DashboardRepository

class DashboardViewmodel(application: Application): AndroidViewModel(application) {

    private val dashboardRepository = DashboardRepository(application)

    private val _sensorStatusLiveData: MutableLiveData<MutableList<SensorRecentResponse>> by lazy{
        MutableLiveData<MutableList<SensorRecentResponse>>()
    }
    val sensorLiveData = liveData {
        emit(dashboardRepository.fetchAllSensors())
    }

    var selectedMarkerSensor: SensorRecentResponse? = null

    val sensorStatusLiveData: LiveData<MutableList<SensorRecentResponse>>
    get() = _sensorStatusLiveData

    private val _averageLatitudeLongitudeLiveData: MutableLiveData<LatLng> by lazy{
        MutableLiveData<LatLng>()
    }
    val averageLatitudeLongitudeLiveData: LiveData<LatLng>
    get() = _averageLatitudeLongitudeLiveData
}