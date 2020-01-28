package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.coroutines.flow.Flow

interface LogRepository {

    fun fetchLogs(): Flow<List<Entity.LogDetails>>
    fun fetchFreshLogs(): Flow<List<Entity.LogDetails>>
    fun fetchLocalLogs(): Flow<List<Entity.LogDetails>>
}