package com.versilistyson.welldone.domain.framework.datasource.log

import com.versilistyson.welldone.data.db.log.LogData
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import kotlinx.coroutines.flow.Flow

interface LogLocalDataSource: BaseDataSource {
    suspend fun save(key: StoreKey.LogKey, log: LogData )
    suspend fun save(key: StoreKey.LogKey, logs: List<LogData>)
    fun getLogs(key: StoreKey.LogKey): Flow<List<LogData>>
}