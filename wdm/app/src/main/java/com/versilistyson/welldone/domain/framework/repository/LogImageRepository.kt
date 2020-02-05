package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.data.api.log.LogImageApi
import com.versilistyson.welldone.data.db.log.LogImageData
import com.versilistyson.welldone.domain.common.RetrofitResult
import com.versilistyson.welldone.domain.framework.entity.Entity
import okhttp3.MultipartBody

interface LogImageRepository {
    suspend fun fetchLogImagesById(logId: Long): List<LogImageData>
    suspend fun refresh(logId: Long): List<LogImageData>
    suspend fun addImageDetailsToLog(file: MultipartBody.Part, metadata: LogImageApi.Dto.LogImageMeta): RetrofitResult<Entity.LogImage>
}