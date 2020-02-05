package com.versilistyson.welldone.domain.framework.datasource.log

import com.versilistyson.welldone.data.api.log.LogImageApi
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import retrofit2.Response

interface LogImageRemoteDataSource: BaseDataSource {
    suspend fun getLogImagesByLogId(key: StoreKey.LogImageKey, logId: Long): Response<List<LogImageApi.Dto.LogImage>>
}