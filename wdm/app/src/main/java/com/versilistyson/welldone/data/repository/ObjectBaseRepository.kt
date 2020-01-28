package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.StoreBuilder
import com.versilistyson.welldone.data.util.Mappable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

@ExperimentalCoroutinesApi
@FlowPreview
abstract class ObjectBaseRepository <RemoteEntity: Mappable<DatabaseEntity>, DatabaseEntity: Any>{

    abstract suspend fun getObjectRemotely(): Response<RemoteEntity>
    abstract fun readObjectFromPersistence(key: (DatabaseEntity) -> Int): Flow<DatabaseEntity>
    abstract suspend fun writeObjectToPersistence(key: (DatabaseEntity) -> Int, sensor: DatabaseEntity)

    protected val objectsStoreBuilder =
        StoreBuilder
            .fromNonFlow<(DatabaseEntity) -> Int, DatabaseEntity> {
                getObjectRemotely().body()?.map()!!
            }.persister(
                reader = ::readObjectFromPersistence,
                writer = ::writeObjectToPersistence
            ).build()
}