package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.domain.framework.datasource.log.LogDataRemoteSource
import com.versilistyson.welldone.domain.framework.datasource.log.LogLocalDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.LogRepository
import retrofit2.Response

class LogRepositoryImpl(private val remoteDataSource: LogDataRemoteSource,
                        private val localDataSource: LogLocalDataSource): LogRepository {

    override fun fetchAllLogsRemotely(sensorId: Long): Response<List<Entity.Log>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}