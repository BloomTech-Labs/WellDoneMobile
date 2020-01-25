package com.versilistyson.welldone.domain.framework.datasource.sensor

import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource

interface SensorRemoteDataSource: BaseDataSource {
    fun getSensors(): List<SensorApi.Dto.SensorRecentResponse>
}