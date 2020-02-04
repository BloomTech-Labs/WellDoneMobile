package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.MemoryPolicy
import com.dropbox.android.external.store4.StoreBuilder
import com.versilistyson.welldone.data.db.log.LogData
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.log.LogRemoteDataSource
import com.versilistyson.welldone.domain.framework.datasource.log.LogLocalDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.LogRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class LogRepositoryImpl @Inject constructor(
    private val remoteDataSource: LogRemoteDataSource,
    private val localDataSource: LogLocalDataSource
) : LogRepository {

    private val store =
        StoreBuilder
            .fromNonFlow<StoreKey.LogKey, List<LogData>> { storeKey ->
                val result = remoteDataSource.fetchAllLogs(storeKey).body()
                val mappedLogs = mutableListOf<LogData>()
                result?.forEach { log ->
                    mappedLogs.add(log.map())
                }
                mappedLogs
            }
            .persister(
                reader = localDataSource::getLogs,
                writer = localDataSource::save
            )
            .cachePolicy(MemoryPolicy.builder().build())
            .build()


    fun refreshLogs() {}

    override fun fetchLogs(): Flow<List<Entity.LogDetails>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchFreshLogs(): Flow<List<Entity.LogDetails>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}