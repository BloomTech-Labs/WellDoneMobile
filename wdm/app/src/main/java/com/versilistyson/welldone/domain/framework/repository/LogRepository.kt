package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.coroutines.flow.Flow

interface LogRepository {

    fun fetchLogs(): Flow<List<Entity.Log>>
    fun fetchFreshLogs(): Flow<List<Entity.Log>>
    fun fetchLocalLogs(): Flow<List<Entity.Log>>
}