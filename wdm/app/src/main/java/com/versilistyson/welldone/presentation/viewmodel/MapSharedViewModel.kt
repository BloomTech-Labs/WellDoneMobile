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
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@InternalCoroutinesApi
class MapSharedViewModel @Inject constructor(
    private val getFreshSensors: GetFreshSensorStreamUseCase,
    private val getCachedSensors: GetCacheSensorStreamUseCase
) : ViewModel() {

    //merge incoming live data from the use case, observer will be notified of the changes
    val liveDataMerger: MediatorLiveData<Either<Failure, ResponseResult<Entity.Sensors>>> by lazy {
        MediatorLiveData<Either<Failure, ResponseResult<Entity.Sensors>>>()
    }

    init {
        liveDataMerger.addSource(freshSensorLiveData){
            liveDataMerger.value = it
        }

        liveDataMerger.addSource(sensorLiveData){
            liveDataMerger.value = it
        }
    }

    private val freshSensorLiveData: LiveData<Either<Failure, ResponseResult<Entity.Sensors>>>
        get() =
            liveData {
                emitSource(getFreshSensors.invoke(viewModelScope, FlowUseCase.None()))
            }

    private val sensorLiveData: LiveData<Either<Failure, ResponseResult<Entity.Sensors>>>
        get() =
            liveData {
                emitSource(getCachedSensors.invoke(viewModelScope, FlowUseCase.None()))
            }

    fun newFetchFreshSensor(){
        freshSensorLiveData
    }

    fun newFetchSensor() {
        sensorLiveData
    }
}
//    enum class State {
//        LOADING_FRESH_SENSORS,
//        LOADING_CACHE_SENSORS,
//        LOADED_FRESH_SENSORS,
//        LOADED_CACHE_SENSORS,
//        FAILURE
//    }

//suspend fun getSensors(coroutineScope: CoroutineScope): LiveData<Either<Failure, ResponseResult<Entity.Sensors>>> {
//           getCachedSensors.invoke(coroutineScope, FlowUseCase.None())
//    }
//    fun getFreshSensors(coroutineScope: CoroutineScope): LiveData<Either<Failure, ResponseResult<Entity.Sensors>>> {
//        return liveData {
//            emitSource(getFreshSensors.invoke(coroutineScope, FlowUseCase.None()))
//        }
//    }