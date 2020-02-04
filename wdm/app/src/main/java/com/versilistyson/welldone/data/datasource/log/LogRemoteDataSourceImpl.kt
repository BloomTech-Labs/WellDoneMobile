package com.versilistyson.welldone.data.datasource.log

import com.versilistyson.welldone.data.api.log.LogApi
import com.versilistyson.welldone.domain.framework.datasource.log.LogRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class LogRemoteDataSourceImpl @Inject constructor(private val logApi: LogApi): LogRemoteDataSource {

    override suspend fun fetchAllLogs() =
        logApi.getAllLogs()

    override suspend fun postNewLog(newLog: LogApi.Dto.LogToPost): Response<Any> =
        logApi.addLog(newLog)


    override suspend fun updateLog(logId: Long, comment: String) =
        logApi.updateLog(logId, comment)

    override suspend fun deleteLogById(logId: Long) =
        logApi.deleteLog(logId)

}