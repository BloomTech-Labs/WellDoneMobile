package com.versilistyson.welldone.data.datasource.sensor

import com.versilistyson.welldone.data.remote.dto.SensorRecentResponse
import com.versilistyson.welldone.domain.common.Result

interface ISensorApiDataSource {
    suspend fun fetchSensors() : Result<List<SensorRecentResponse>>
}