package com.versilistyson.welldone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.usecases.GetSensorUseCase
import com.versilistyson.welldone.domain.framework.usecases.UseCase

class DashboardViewmodel(private val getSensorUseCase: GetSensorUseCase): ViewModel() {

    private val _sensorLiveData: MutableLiveData<List<Entity.Sensor>> by lazy{
        MutableLiveData<List<Entity.Sensor>>()
    }
    val sensorLiveData: LiveData<List<Entity.Sensor>>
    get() = _sensorLiveData

    fun loadData(){
        getSensorUseCase.invoke(viewModelScope, UseCase.NoParams()) {
            it.either(getSensorUseCase::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(sensors: List<Entity.Sensor>){
        _sensorLiveData.value = sensors
    }
}