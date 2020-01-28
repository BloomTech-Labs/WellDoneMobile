package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.StoreBuilder
import com.versilistyson.welldone.data.util.Mappable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

//handles the fetching from remote and local data sources
@ExperimentalCoroutinesApi
@FlowPreview
abstract class ObjectsBaseRepository <RemoteEntity: Mappable<DatabaseEntity>, DatabaseEntity: Any>{

    abstract suspend fun getObjectsRemotely(): Response<List<RemoteEntity>>
    abstract fun readObjectsFromPersistence(keyList: (List<DatabaseEntity>) -> List<Int>): Flow<List<DatabaseEntity>>
    abstract suspend fun writeObjectsToPersistence(keyList: (List<DatabaseEntity>) -> List<Int>, sensors: List<DatabaseEntity>)

    protected val objectsStoreBuilder =
        StoreBuilder
            .fromNonFlow<(List<DatabaseEntity>) -> List<Int>, List<DatabaseEntity>> {
                val objectList = mutableListOf<DatabaseEntity>()
                getObjectsRemotely().body()?.forEach {
                    objectList.add(it.map())
                }
                objectList
            }.persister(
                reader = ::readObjectsFromPersistence,
                writer = ::writeObjectsToPersistence
            ).build()
}


//open suspend fun fetchNetworkObjects(
//        getFromNetwork: suspend () -> Response<List<RemoteEntity>>): Result<List<DatabaseEntity>> {
//        try {
//            val remoteResponse = getFromNetwork.invoke()
//            return if (remoteResponse.isSuccessful) {
//                when (remoteResponse.body()) {
//                    null -> {
//                        Result.success(code = remoteResponse.code())
//                    }
//                    else -> {
//                        Result.success(remoteResponse.body()!!.map { it.map() }, code = remoteResponse.code())
//                    }
//                }
//            } else {
//                Result.networkError(code = remoteResponse.code())
//            }
//        } catch (e: Exception) {
//            return Result.networkError(e)
//        }
//    }
//
//    open suspend fun fetchNetworkObject(
//        getFromNetwork: suspend () -> Response<RemoteEntity>): Result<DatabaseEntity> {
//        return try {
//            val remoteResponse = getFromNetwork.invoke()
//            if (remoteResponse.isSuccessful) {
//                when (remoteResponse.body()) {
//                    null -> {
//                        Result.success(null, remoteResponse.code())
//                    }
//                    else -> {
//                        Result.success(remoteResponse.body()!!.map(), remoteResponse.code())
//                    }
//                }
//            } else {
//                Result.networkError(code = remoteResponse.code())
//            }
//        } catch (e: Exception) {
//            return Result.networkError(e)
//        }
//    }
//
//    open suspend fun fetchLocalObjects(getFromDataBase: suspend () -> List<DatabaseEntity>): Result<List<DomainEntity>> {
//        return try {
//            when (val dbResponse = getFromDataBase.invoke()) {
//                null -> {
//                    Result.success()
//                }
//                else -> {
//                    Result.success(dbResponse.map { dbEntities -> dbEntities.map() })
//                }
//            }
//        } catch (e: Exception) {
//            Result.localError(e)
//        }
//    }
//
//    open suspend fun fetchLocalObject(objectId: Long, getFromDatabase: suspend (Long) -> DatabaseEntity): Result<DomainEntity> {
//        return try {
//            when(val dbResponse = getFromDatabase.invoke(objectId)) {
//                null-> {
//                    Result.success()
//                }
//                else -> {
//                    Result.success(dbResponse.map())
//                }
//            }
//        } catch(e: Exception) {
//            Result.localError(e)
//        }
//    }
//
//    open suspend fun saveLocalObject(databaseEntity: DatabaseEntity,
//                                     saveToDatabase: suspend (DatabaseEntity) -> DatabaseEntity): Result<DatabaseEntity>{
//        return try {
//            val dbResponse = saveToDatabase.invoke(databaseEntity)
//            Result.success(dbResponse)
//        } catch(e: Exception) {
//            Result.localError(e)
//        }
//    }
//
//    open suspend fun saveLocalObjects(databaseEntities: List<DatabaseEntity>,
//                                      saveToDatabase: suspend (List<DatabaseEntity>) -> List<DatabaseEntity>): Result<List<DomainEntity>>{
//        return try {
//            val dbResponse = saveToDatabase.invoke(databaseEntities)
//                .map { databaseEntity ->
//                    databaseEntity.map()
//                }
//            Result.success(dbResponse)
//        } catch(e: Exception) {
//            Result.localError(e)
//        }
//    }