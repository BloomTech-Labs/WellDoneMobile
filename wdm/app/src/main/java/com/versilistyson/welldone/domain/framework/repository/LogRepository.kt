package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.data.api.log.LogApi
import com.versilistyson.welldone.domain.common.RetrofitResult
import com.versilistyson.welldone.domain.framework.entity.Entity

interface LogRepository {

    suspend fun fetchLogsBySensorId(sensorId: Long): List<Entity.LogDetails>
    suspend fun refresh(sensorId: Long): List<Entity.LogDetails>
    suspend fun addLog(logToPost: LogApi.Dto.LogToPost): RetrofitResult<Entity.LogDetails>
//    fun fetchFreshLogs(): Flow<List<Entity.LogDetails>>
//    fun fetchLocalLogs(): Flow<List<Entity.LogDetails>>
}