package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.domain.framework.datasource.log.LogImageLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.log.LogImageRemoteDataSource
import com.versilistyson.welldone.domain.framework.repository.LogImageRepository
import javax.inject.Inject

class LogImageRepositoryImpl @Inject constructor(localDataSource: LogImageLocalDataSource,
                                                 remoteDataSource: LogImageRemoteDataSource): LogImageRepository {


}