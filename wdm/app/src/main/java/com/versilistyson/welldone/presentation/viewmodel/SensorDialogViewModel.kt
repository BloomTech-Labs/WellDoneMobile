package com.versilistyson.welldone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.versilistyson.welldone.domain.framework.entity.Entity
import javax.inject.Inject

//just need to get the list of logs for recycler view and persist from screen rotation
class SensorDialogViewModel @Inject constructor() : ViewModel() {

    private val _listOfLogs = MutableLiveData<MutableList<Entity.LogDetails>>()

    val listOfLogs: LiveData<MutableList<Entity.LogDetails>>
        get() = _listOfLogs

    init {
        _listOfLogs.value =  mutableListOf(
            Entity.LogDetails(
                1,
                "13/05/2019",
                "06/06/2019",
                null,
                "This sensor had a loose valve."
            ),
            Entity.LogDetails(
                2,
                "14/03/2019",
                "14/05/2019",
                2,
                "This sensor had network connectivity issues."
            )
        )
    }

    //adds it to top of recycler view
    fun addLog(log: Entity.LogDetails){
        _listOfLogs.value?.let {
            _listOfLogs.value = it.apply{ add(0, log) }
        }
    }

    fun updateLog(log: Entity.LogDetails, index: Int){
        _listOfLogs.value?.let {
            _listOfLogs.value = it.apply{ it[index] = log }
        }
    }
}