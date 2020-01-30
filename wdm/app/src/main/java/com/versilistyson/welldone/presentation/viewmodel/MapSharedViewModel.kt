package com.versilistyson.welldone.presentation.viewmodel

import androidx.lifecycle.*
import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.common.ResponseResult
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.usecases.common.FlowUseCase
import com.versilistyson.welldone.domain.framework.usecases.sensor.GetCacheSensorStreamUseCase
import com.versilistyson.welldone.domain.framework.usecases.sensor.GetFreshSensorStreamUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@InternalCoroutinesApi
class MapSharedViewModel @Inject constructor(
    private val getFreshSensors: GetFreshSensorStreamUseCase,
    private val getCachedSensors: GetCacheSensorStreamUseCase
) : ViewModel() {

    /*
        First, we normally always get data from cached. Occasionally the user might refresh the sensor data, which
        means store will call its fetcher to fresh new sensors, and update the local persistence and store's cache.
        We need 1 live data instance that both use case streams will map to.
     */
    private var cachedStreamSensorLiveData =
        liveData {
            emitSource(getCachedSensors.invoke(viewModelScope, FlowUseCase.None()))
        }
    private val sensorLiveData = MediatorLiveData<Either<Failure, ResponseResult<Entity.Sensors>>>()

    init{
        sensorLiveData.addSource(cachedStreamSensorLiveData){
            sensorLiveData.value = it
        }
    }

    fun getFreshNewSensors(coroutineContext: CoroutineContext = viewModelScope){
        liveData(coroutineContext) {
            emitSource()
        }
    }
}
//liveDataMerger.addSource(freshSensorLiveData) {
//    liveDataMerger.value = it
//}
//
//    init {
//        liveDataMerger.addSource(sensorLiveData){
//            liveDataMerger.value = it
//        }
//    }
//
//
//    private val sensorLiveData: LiveData<Either<Failure, ResponseResult<Entity.Sensors>>>
//        get() =
//            liveData {
//                emitSource(getCachedSensors.invoke(viewModelScope, FlowUseCase.None()))
//            }
//
//    fun newFetchFreshSensor(){
//        freshSensorLiveData
//    }
//
//    fun newFetchSensor() {
//        sensorLiveData
//    }