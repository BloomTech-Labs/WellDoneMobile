package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.domain.framework.datasource.log.LogDataRemoteSource
import com.versilistyson.welldone.domain.framework.datasource.log.LogLocalDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.LogRepository
import kotlinx.coroutines.flow.Flow

class LogRepositoryImpl(private val remoteDataSource: LogDataRemoteSource,
                        private val localDataSource: LogLocalDataSource): LogRepository {

    override fun fetchLogs(): Flow<List<Entity.LogDetails>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchFreshLogs(): Flow<List<Entity.LogDetails>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchLocalLogs(): Flow<List<Entity.LogDetails>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}