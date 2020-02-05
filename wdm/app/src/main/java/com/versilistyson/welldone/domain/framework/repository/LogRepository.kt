package com.versilistyson.welldone.domain.framework.repository

import com.dropbox.android.external.store4.StoreResponse
import com.versilistyson.welldone.data.db.log.LogData
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.coroutines.flow.Flow

interface LogRepository {

    suspend fun refresh(storeKey: StoreKey.LogKey): List<LogData>
    //    fun fetchLocalLogs(): Flow<List<Entity.LogDetails>>
    fun fetchLogs(storeKey: StoreKey.LogKey): Flow<StoreResponse<List<LogData>>>
}