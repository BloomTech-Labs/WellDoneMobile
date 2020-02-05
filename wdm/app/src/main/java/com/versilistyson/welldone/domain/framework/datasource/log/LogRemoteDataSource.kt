package com.versilistyson.welldone.domain.framework.datasource.log

import com.versilistyson.welldone.data.api.log.LogApi
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import retrofit2.Response

interface LogRemoteDataSource: BaseDataSource {

    suspend fun getLogsBySensorId(key: StoreKey.LogKey): Response<List<LogApi.Dto.Log>>
    suspend fun addLogToSensorId(log: LogApi.Dto.LogToPost): Response<LogApi.Dto.Log>
}