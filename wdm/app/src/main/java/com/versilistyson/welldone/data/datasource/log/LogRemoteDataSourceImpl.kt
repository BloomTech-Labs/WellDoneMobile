package com.versilistyson.welldone.data.datasource.log

import com.versilistyson.welldone.data.api.log.LogApi
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.log.LogRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class LogRemoteDataSourceImpl @Inject constructor(private val logApi: LogApi): LogRemoteDataSource {

    override suspend fun getLogsBySensorId(key: StoreKey.LogKey): Response<List<LogApi.Dto.Log>> =
        logApi.getLogs(key.id)

    override suspend fun addLogToSensorId(log: LogApi.Dto.LogToPost): Response<LogApi.Dto.Log> =
        logApi.addLog(log)
}