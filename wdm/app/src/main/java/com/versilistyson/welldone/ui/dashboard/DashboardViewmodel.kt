package com.versilistyson.welldone.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.versilistyson.welldone.data.remote.dto.PumpResponse
import com.versilistyson.welldone.repository.DashboardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DashboardViewmodel: ViewModel() {

    private val dashboardRepository = DashboardRepository()

    var pumpLiveData = MutableLiveData<Response<List<PumpResponse>>>()

    fun fetchAllPumps(){
        viewModelScope.launch(Dispatchers.IO) {
            pumpLiveData.value = dashboardRepository.fetchAllPumps()
        }
    }
}