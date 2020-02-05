package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.MemoryPolicy
import com.dropbox.android.external.store4.StoreBuilder
import com.dropbox.android.external.store4.fresh
import com.dropbox.android.external.store4.get
import com.versilistyson.welldone.data.api.log.LogApi
import com.versilistyson.welldone.data.db.log.LogData
import com.versilistyson.welldone.data.util.MEMORY_CACHE_TIME
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.common.RetrofitResult
import com.versilistyson.welldone.domain.framework.datasource.log.LogLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.log.LogRemoteDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.LogRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class LogRepositoryImpl @Inject constructor(private val remoteDataSource: LogRemoteDataSource,
                                            private val localDataSource: LogLocalDataSource): LogRepository {

    val store =
        StoreBuilder
            .fromNonFlow<StoreKey.LogKey, List<LogData>>{ storeKey ->
                val logs = mutableListOf<LogData>()
                remoteDataSource.getLogsBySensorId(storeKey).body()?.forEach {
                    logs.add(it.map())
                }
                logs
            }.persister(
                reader = localDataSource::getLogsBySensorId,
                writer = localDataSource::saveLogs
            ).cachePolicy(
                MemoryPolicy.builder()
                    .setMemorySize(100)
                    .setExpireAfterAccess(MEMORY_CACHE_TIME)
                    .setExpireAfterTimeUnit(TimeUnit.DAYS)
                    .build()
            ).build()

    override suspend fun fetchLogsBySensorId(sensorId: Long): List<Entity.LogDetails> =
        store.get(StoreKey.LogKey(sensorId)).map {
            it.map()
        }

    override suspend fun refresh(sensorId: Long): List<Entity.LogDetails> =
        store.fresh(StoreKey.LogKey(sensorId)).map {
            it.map()
        }

    override suspend fun addLog(logToPost: LogApi.Dto.LogToPost): RetrofitResult<Entity.LogDetails> {
        val response = remoteDataSource.addLogToSensorId(logToPost)
        if(response.isSuccessful){
            val mappedLog = response.body()?.map() ?: return RetrofitResult.Error()
            localDataSource.save(StoreKey.LogKey(mappedLog.logId), mappedLog)
            return RetrofitResult.Data(mappedLog.map())
        } else {
            return RetrofitResult.Error(response.message(), response.code())
        }
    }
}