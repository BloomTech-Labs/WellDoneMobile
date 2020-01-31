package com.versilistyson.welldone.domain.framework.datasource.sensor

import com.versilistyson.welldone.data.api.sensor.SensorApi
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import retrofit2.Response

interface SensorRemoteDataSource: BaseDataSource {
    suspend fun getSensors(): Response<List<SensorApi.Dto.SensorRecentResponse>>
}