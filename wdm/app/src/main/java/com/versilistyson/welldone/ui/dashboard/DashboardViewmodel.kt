package com.versilistyson.welldone.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.mapbox.mapboxsdk.geometry.LatLng
import com.versilistyson.welldone.data.remote.dto.SensorRecentResponse
import com.versilistyson.welldone.repository.DashboardRepository

class DashboardViewmodel(application: Application): AndroidViewModel(application) {

    private val dashboardRepository = DashboardRepository(application)

    val sensorLiveData = liveData {
        emit(dashboardRepository.fetchAllSensors())
    }

    private val _sensorStatusLiveData: MutableLiveData<MutableList<SensorRecentResponse>> by lazy{
        MutableLiveData<MutableList<SensorRecentResponse>>()
    }

    var selectedMarkerSensor: SensorRecentResponse? = null

    val sensorStatusLiveData: LiveData<MutableList<SensorRecentResponse>>
    get() = _sensorStatusLiveData

    private val _averageLatitudeLongitudeLiveData: MutableLiveData<LatLng> by lazy{
        MutableLiveData<LatLng>()
    }

    val averageLatitudeLongitudeLiveData: LiveData<LatLng>
    get() = _averageLatitudeLongitudeLiveData

    fun addSensorStatus(sensorRecentResponse: SensorRecentResponse){
        sensorStatusLiveData.value?.let{
            it.add(sensorRecentResponse)
        }
    }

//    fun clickedOnPumpMarker(marker: Marker){
//        selectedMarkerSensor = marker.tag as SensorRecentResponse
//    }
//
//    fun getAverageLatitudeLongitude(sensors: List<SensorRecentResponse>, mMap: MapboxMap){
//
//        viewModelScope.launch(Dispatchers.Main) {
//            var totalLat = 0.0
//            var totalLng = 0.0
//
//            for (sensor in sensors) {
//                val point = LatLng(sensor.latitude, sensor.longitude)
//                totalLat += point.latitude
//                totalLng += point.longitude
//
//                val marker = MarkerOptions()
//                    .position(point)
//
//                when (sensor.status) {
//                    null -> marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.no_data_marker))
//                    1 -> marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.non_working_marker))
//                    2 -> marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.working_marker))
//                }
//
//                mMap.addMarker(marker).tag = sensor
//            }
//
//            _averageLatitudeLongitudeLiveData.postValue(LatLng(totalLat, totalLng))
//        }
//    }
}