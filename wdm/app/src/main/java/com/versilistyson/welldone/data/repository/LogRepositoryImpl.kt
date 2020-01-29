package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.domain.framework.datasource.log.LogRemoteDataSource
import com.versilistyson.welldone.domain.framework.datasource.log.LogLocalDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.LogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogRepositoryImpl @Inject constructor(private val remoteDataSource: LogRemoteDataSource,
                                            private val localDataSource: LogLocalDataSource): LogRepository {

    override fun fetchLogs(): Flow<List<Entity.LogDetails>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchFreshLogs(): Flow<List<Entity.LogDetails>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}