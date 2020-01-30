package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.data.db.log.LogData
import com.versilistyson.welldone.domain.framework.entity.Entity
import retrofit2.Response

interface LogRepository {

    fun fetchAllLogsRemotely(): Response<List<LogData>>
    fun saveLogsLocally(logs: List<LogData>): Response<Entity.Log>
    fun saveLogLocally(log: LogData): Response<LogData>
    fun fetchLogsLocally(): Response<List<Entity.Log>>
    fun fetchLogLocally(): Response<Entity.Log>
}