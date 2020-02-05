package com.versilistyson.welldone.data.datasource.log

import com.versilistyson.welldone.data.api.log.LogImageApi
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.log.LogImageRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class LogImageRemoteDataSourceImpl @Inject constructor(val logImageApi: LogImageApi): LogImageRemoteDataSource {

    override suspend fun getLogImagesByLogId(key: StoreKey.LogImageKey, logId: Long): Response<List<LogImageApi.Dto.LogImage>> =
        logImageApi.getImagesByLogId(logId)
}