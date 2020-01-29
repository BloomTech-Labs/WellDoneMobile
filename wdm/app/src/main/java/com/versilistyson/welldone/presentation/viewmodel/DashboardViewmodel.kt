package com.versilistyson.welldone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.usecases.GetSensorsUseCase
import com.versilistyson.welldone.domain.framework.usecases.UseCase
import kotlinx.coroutines.InternalCoroutinesApi

@UseExperimental(InternalCoroutinesApi::class)
class DashboardViewmodel (private val getSensorsUseCase: GetSensorsUseCase): ViewModel() {

    private val _sensorLiveData: MutableLiveData<Entity.Sensors> by lazy{
        MutableLiveData<Entity.Sensors>()
    }
    val sensorLiveData: LiveData<Entity.Sensors>
    get() = _sensorLiveData

    @InternalCoroutinesApi
    fun loadData(){
        getSensorsUseCase.invoke(viewModelScope, UseCase.None()) {
            TODO()
        }
    }

    private fun handleSuccess(sensors: Entity.Sensors){
        _sensorLiveData.value = sensors
    }
}