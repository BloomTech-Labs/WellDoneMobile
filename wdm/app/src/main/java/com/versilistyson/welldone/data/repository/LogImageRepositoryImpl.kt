package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.*
import com.versilistyson.welldone.data.api.log.LogImageApi
import com.versilistyson.welldone.data.db.log.LogImageData
import com.versilistyson.welldone.data.util.MEMORY_CACHE_TIME
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.common.RetrofitResult
import com.versilistyson.welldone.domain.framework.datasource.log.LogImageLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.log.LogImageRemoteDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.LogImageRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import okhttp3.MultipartBody
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class LogImageRepositoryImpl @Inject constructor(private val localDataSource: LogImageLocalDataSource,
                                                 private val remoteDataSource: LogImageRemoteDataSource): LogImageRepository {

    private var store =
        StoreBuilder
            .fromNonFlow<StoreKey.LogImageKey, List<LogImageData>> { storekey ->
                val logImages = mutableListOf<LogImageData>()
                remoteDataSource.getLogImagesByLogId(storekey).body()?.forEach {
                    logImages.add(it.map())
                }
                logImages
            }
            .persister(
                reader = localDataSource::getLogImagesById,
                writer = localDataSource::saveLogImages
            ).cachePolicy(
                MemoryPolicy.builder()
                    .setMemorySize(100)
                    .setExpireAfterAccess(MEMORY_CACHE_TIME)
                    .setExpireAfterTimeUnit(TimeUnit.DAYS)
                    .build()
            ).build()

    override suspend fun fetchLogImagesById(logId: Long): List<LogImageData> =
        store.get(StoreKey.LogImageKey(logId))

    override suspend fun refresh(logId: Long): List<LogImageData> =
        store.fresh(StoreKey.LogImageKey(logId))

    override suspend fun addImageDetailsToLog(file: MultipartBody.Part, metadata: LogImageApi.Dto.LogImageMeta): RetrofitResult<Entity.LogImage> {
        val response = remoteDataSource.addLogImageByLogId(file, metadata)
        if(response.isSuccessful){
            val mappedLogImage = response.body()?.map() ?: return RetrofitResult.Error()
            localDataSource.saveLogImage(StoreKey.LogImageKey(mappedLogImage.logId), mappedLogImage)
            return RetrofitResult.Data(mappedLogImage.map())
        } else {
            return RetrofitResult.Error(response.message(), response.code())
        }
    }
}