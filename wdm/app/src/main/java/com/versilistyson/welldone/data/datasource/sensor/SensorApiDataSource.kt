package com.versilistyson.welldone.data.datasource.sensor

import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.datasource.BaseDataSource
import com.versilistyson.welldone.data.remote.dto.SensorRecentResponse
import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.entity.Entity

class SensorApiDataSource(private val api: SensorApi) : ISensorApiDataSource, BaseDataSource() {
    override suspend fun fetchSensors(): Result<List<SensorRecentResponse>> = getResult { api.getSensors() }
}