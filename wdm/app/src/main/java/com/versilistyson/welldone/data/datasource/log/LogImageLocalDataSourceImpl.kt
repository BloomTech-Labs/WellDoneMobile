package com.versilistyson.welldone.data.datasource.log

import com.versilistyson.welldone.data.db.log.LogImageData
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.log.LogImageLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogImageLocalDataSourceImpl @Inject constructor(): LogImageLocalDataSource {

    override fun getLogImages(key: StoreKey.LogImageKey): Flow<List<LogImageData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveLogImages(key: StoreKey.LogImageKey, logImages: List<LogImageData>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}