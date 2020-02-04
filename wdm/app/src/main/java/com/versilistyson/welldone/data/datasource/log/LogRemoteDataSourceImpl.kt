package com.versilistyson.welldone.data.datasource.log

import com.versilistyson.welldone.data.api.log.LogApi
import com.versilistyson.welldone.domain.framework.datasource.log.LogRemoteDataSource
import javax.inject.Inject

class LogRemoteDataSourceImpl @Inject constructor(private val logApi: LogApi): LogRemoteDataSource {
    override suspend fun getLogsBySensorId(sensorId: Long) =
        logApi.getLogs(sensorId)

    override suspend fun addNewLog(logToAdd: LogApi.Dto.Log) =
        logApi.addLog(logToAdd)

    override suspend fun updateLog(logId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteLogById(logId: Long) =
        logApi.deleteLog(logId)

}