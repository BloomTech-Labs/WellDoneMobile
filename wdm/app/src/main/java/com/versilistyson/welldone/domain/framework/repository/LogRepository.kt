package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.domain.framework.entity.Entity
import retrofit2.Response

interface LogRepository {

    fun fetchAllLogsRemotely(sensorId: Long): Response<List<Entity.Log>>
}