package com.versilistyson.welldone.domain.framework.datasource.log

import com.versilistyson.welldone.data.api.log.LogImageApi
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import okhttp3.MultipartBody
import retrofit2.Response

interface LogImageRemoteDataSource: BaseDataSource {
    suspend fun getLogImagesByLogId(key: StoreKey.LogImageKey): Response<List<LogImageApi.Dto.LogImage>>
    suspend fun addLogImageByLogId(file: MultipartBody.Part, metadata: LogImageApi.Dto.LogImageMeta): Response<LogImageApi.Dto.LogImage>
}