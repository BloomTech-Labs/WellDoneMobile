package com.versilistyson.welldone.data.datasource.log

import com.versilistyson.welldone.data.api.log.LogApi
import com.versilistyson.welldone.data.db.log.LogDao
import com.versilistyson.welldone.data.db.log.LogData
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.log.LogLocalDataSource
import javax.inject.Inject

class LogLocalDataSourceImpl @Inject constructor(private val logDao: LogDao): LogLocalDataSource {
    override suspend fun save(key: StoreKey.LogKey, log: LogData) =
        logDao.save(log)

    override suspend fun save(key: StoreKey.LogKey, logs: List<LogData>) =
        logDao.saveAll(logs)

    override fun getLogs(key: StoreKey.LogKey) =
        logDao.getLogs()
}