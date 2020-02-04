package com.versilistyson.welldone.domain.framework.datasource.log

import com.versilistyson.welldone.data.api.log.LogApi
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import retrofit2.Response

interface LogRemoteDataSource: BaseDataSource {
    suspend fun fetchAllLogs(storeKey: StoreKey.LogKey): Response<List<LogApi.Dto.Log>>
    suspend fun postNewLog(newLog: LogApi.Dto.LogToPost): Response<Any>
    suspend fun updateLog(logId: Long, comment: String): Response<LogApi.Dto.Log>
    suspend fun deleteLogById(logId: Long): Response<LogApi.Dto.Log>
}