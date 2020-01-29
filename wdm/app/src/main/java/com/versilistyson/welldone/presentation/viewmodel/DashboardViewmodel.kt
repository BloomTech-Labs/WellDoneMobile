package com.versilistyson.welldone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.usecases.sensor.GetCacheSensorStreamUseCase
import com.versilistyson.welldone.domain.framework.usecases.common.UseCase
import kotlinx.coroutines.InternalCoroutinesApi

class DashboardViewmodel @UseExperimental(InternalCoroutinesApi::class) constructor(private val getSensorUseCase: GetCacheSensorStreamUseCase): ViewModel() {

    private val _sensorLiveData: MutableLiveData<Entity.Sensors> by lazy{
        MutableLiveData<Entity.Sensors>()
    }
    val sensorLiveData: LiveData<Entity.Sensors>
    get() = _sensorLiveData

    @InternalCoroutinesApi
    fun loadData(){
        getSensorUseCase.invoke(viewModelScope, UseCase.None()) {
            TODO()
        }
    }

    private fun handleSuccess(sensors: Entity.Sensors){
        _sensorLiveData.value = sensors
    }
}