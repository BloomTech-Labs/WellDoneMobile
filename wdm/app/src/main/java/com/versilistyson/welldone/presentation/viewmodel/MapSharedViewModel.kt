package com.versilistyson.welldone.presentation.viewmodel

import androidx.lifecycle.*
import com.versilistyson.welldone.domain.framework.usecases.common.FlowUseCase
import com.versilistyson.welldone.domain.framework.usecases.sensor.GetCacheSensorStreamUseCase
import com.versilistyson.welldone.domain.framework.usecases.sensor.GetFreshSensorStreamUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
class MapSharedViewModel @Inject constructor(
    private val freshSensorStreamUseCase: GetFreshSensorStreamUseCase,
    private val cacheSensorStreamUseCase: GetCacheSensorStreamUseCase
) : ViewModel() {

    /*
        When we transform the flow to live data, that live data needs to be observed in order to collect the element's
        it contains. If we initialize fetch fresh sensors multiple times, store will automatically coalesce them into one
        single flow
    */

    //this will try to fetch from the internet first, if that fails, will try to fetch from cache/persister
    fun fetchSensorsThroughCache() =
        liveData {
            emitSource(cacheSensorStreamUseCase.invoke(viewModelScope, FlowUseCase.None()))
        }

    //this will call the fetcher and
    fun fetchFreshSensors() =
        liveData {
            emitSource(freshSensorStreamUseCase.invoke(viewModelScope, FlowUseCase.None()))
        }
}