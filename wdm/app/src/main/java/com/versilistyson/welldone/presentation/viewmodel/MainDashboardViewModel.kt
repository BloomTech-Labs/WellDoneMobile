package com.versilistyson.welldone.presentation.viewmodel

import androidx.lifecycle.*
import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.common.ResponseResult
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.usecases.common.FlowUseCase
import com.versilistyson.welldone.domain.framework.usecases.sensor.GetCacheSensorStreamUseCase
import com.versilistyson.welldone.domain.framework.usecases.sensor.GetFreshSensorStreamUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
class MainDashboardViewModel @Inject constructor(): ViewModel() {

    private val _sensorsLiveData: MutableLiveData<Entity.Sensors> by lazy {
        MutableLiveData<Entity.Sensors>()
    }
    val sensorsLiveData: LiveData<Entity.Sensors>
        get() = _sensorsLiveData

    fun updateSensorLiveDataValue(sensors: Entity.Sensors){
        _sensorsLiveData.value = sensors
    }
}


//    val freshSensorLiveData: LiveData<Either<Failure, ResponseResult<Entity.Sensors>>>
//    get() =
//        liveData {
//            emitSource(getFreshSensors.invoke(viewModelScope, FlowUseCase.None()))
//        }
//
//    val cachedSensorLiveData: LiveData<Either<Failure, ResponseResult<Entity.Sensors>>>
//    get() =
//        liveData {
//            emitSource(getCachedSensors.invoke(viewModelScope, FlowUseCase.None()))
//        }