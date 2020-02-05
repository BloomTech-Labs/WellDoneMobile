package com.versilistyson.welldone.data.datasource.log

import com.versilistyson.welldone.data.db.log.LogImageDao
import com.versilistyson.welldone.data.db.log.LogImageData
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.log.LogImageLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogImageLocalDataSourceImpl @Inject constructor(private val logImageDao: LogImageDao): LogImageLocalDataSource {

    override fun getLogImagesById(key: StoreKey.LogImageKey): Flow<List<LogImageData>> =
        logImageDao.getAllLogImagesByLogId(key.id)

    override suspend fun saveLogImages(key: StoreKey.LogImageKey, logImages: List<LogImageData>) {
        logImageDao.save(logImages)
    }

    override suspend fun saveLogImage(key: StoreKey.LogImageKey, logImage: LogImageData) =
        logImageDao.save(logImage)
}