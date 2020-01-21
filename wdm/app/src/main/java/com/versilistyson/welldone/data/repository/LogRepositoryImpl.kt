package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.domain.framework.datasource.log.LogDataRemoteSource
import com.versilistyson.welldone.domain.framework.datasource.log.LogLocalDataSource
import com.versilistyson.welldone.domain.framework.repository.LogRepository

class LogRepositoryImpl(private val remoteDataSource: LogDataRemoteSource,
                        private val localDataSource: LogLocalDataSource): LogRepository {

}