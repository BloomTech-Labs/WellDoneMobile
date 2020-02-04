package com.versilistyson.welldone.domain.framework.datasource.log

import com.versilistyson.welldone.data.api.log.LogApi
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import retrofit2.Response

interface LogRemoteDataSource: BaseDataSource {
    suspend fun getLogsBySensorId(sensorId: Long): Response<List<LogApi.Dto.Log>>
    suspend fun addNewLog(logToAdd: LogApi.Dto.Log): Response<Any>
    suspend fun updateLog(logId: Long)
    suspend fun deleteLogById(logId: Long)
}