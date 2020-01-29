package com.versilistyson.welldone.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.versilistyson.welldone.domain.framework.entity.Entity

//just need to get the list of logs for recyclerview and persist from screen rotation
class SensorDialogViewModel : ViewModel() {

    val listOfLogs by lazy {
        mutableListOf<Entity.LogDetails>()
    }
}