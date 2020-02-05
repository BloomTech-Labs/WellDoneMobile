package com.versilistyson.welldone.data.datasource.log

import com.versilistyson.welldone.data.api.log.LogImageApi
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.log.LogImageRemoteDataSource
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class LogImageRemoteDataSourceImpl @Inject constructor(private val logImageApi: LogImageApi): LogImageRemoteDataSource {

    override suspend fun getLogImagesByLogId(key: StoreKey.LogImageKey): Response<List<LogImageApi.Dto.LogImage>> =
        logImageApi.getImagesByLogId(key.id)

    override suspend fun addLogImageByLogId(
        file: MultipartBody.Part,
        metadata: LogImageApi.Dto.LogImageMeta
    ) = logImageApi.addImageDetailsToLog(file, metadata)
}