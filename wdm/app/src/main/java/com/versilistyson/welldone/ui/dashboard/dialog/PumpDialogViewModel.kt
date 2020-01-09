package com.versilistyson.welldone.ui.dashboard.dialog

import androidx.lifecycle.ViewModel
import com.versilistyson.welldone.data.remote.dto.OperatorLog

//just need to get the list of logs for recyclerview and persist from screen rotation
class PumpDialogViewModel : ViewModel() {

    val listOfLogs by lazy {
        mutableListOf<OperatorLog>()
    }
}