package com.versilistyson.welldone.data.datasource.sensor

import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import retrofit2.Response

class SensorRemoteDataSourceImpl(private val api: SensorApi): SensorRemoteDataSource {
    override suspend fun getSensors(): Response<List<SensorApi.Dto.SensorRecentResponse>> {
        return api.getSensors()
    }
}