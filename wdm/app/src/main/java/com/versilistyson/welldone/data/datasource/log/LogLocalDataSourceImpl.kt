package com.versilistyson.welldone.data.datasource.log

import com.versilistyson.welldone.data.db.log.LogDao
import com.versilistyson.welldone.data.db.log.LogData
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.log.LogLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogLocalDataSourceImpl @Inject constructor(private val logDao: LogDao): LogLocalDataSource {

    override fun getLogsBySensorId(key: StoreKey.LogKey): Flow<List<LogData>> =
        logDao.getAllLogsBySensorId(key.id)

    override suspend fun saveLogs(key: StoreKey.LogKey, logs: List<LogData>) =
        logDao.save(logs)

    override suspend fun save(key: StoreKey.LogKey, log: LogData) =
        logDao.save(log)
}