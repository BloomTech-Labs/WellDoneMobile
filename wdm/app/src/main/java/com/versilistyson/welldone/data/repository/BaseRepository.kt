package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.data.util.Mappable
import com.versilistyson.welldone.domain.common.Result
import retrofit2.Response
//handles the fetching from remote and local data sources
abstract class BaseRepository<RemoteEntity : Mappable<out DatabaseEntity>, DatabaseEntity : Mappable<out DomainEntity>, out DomainEntity> {

    open suspend fun fetchNetworkObjects(
        getFromNetwork: suspend () -> Response<List<RemoteEntity>>): Result<List<DatabaseEntity>> {
        try {
            val remoteResponse = getFromNetwork.invoke()
            return if (remoteResponse.isSuccessful) {
                when (remoteResponse.body()) {
                    null -> {
                        Result.success(code = remoteResponse.code())
                    }
                    else -> {
                        Result.success(remoteResponse.body()!!.map { it.map() }, code = remoteResponse.code())
                    }
                }
            } else {
                Result.networkError(code = remoteResponse.code())
            }
        } catch (e: Exception) {
            return Result.networkError(e)
        }
    }

    open suspend fun fetchNetworkObject(
        getFromNetwork: suspend () -> Response<RemoteEntity>): Result<DatabaseEntity> {
        return try {
            val remoteResponse = getFromNetwork.invoke()
            if (remoteResponse.isSuccessful) {
                when (remoteResponse.body()) {
                    null -> {
                        Result.success(null, remoteResponse.code())
                    }
                    else -> {
                        Result.success(remoteResponse.body()!!.map(), remoteResponse.code())
                    }
                }
            } else {
                Result.networkError(code = remoteResponse.code())
            }
        } catch (e: Exception) {
            return Result.networkError(e)
        }
    }

    open suspend fun fetchLocalObjects(getFromDataBase: suspend () -> List<DatabaseEntity>): Result<List<DomainEntity>> {
        return try {
            when (val dbResponse = getFromDataBase.invoke()) {
                null -> {
                    Result.success()
                }
                else -> {
                    Result.success(dbResponse.map { dbEntities -> dbEntities.map() })
                }
            }
        } catch (e: Exception) {
            Result.localError(e)
        }
    }

    open suspend fun fetchLocalObject(objectId: Long, getFromDatabase: suspend (Long) -> DatabaseEntity): Result<DomainEntity> {
        return try {
            when(val dbResponse = getFromDatabase.invoke(objectId)) {
                null-> {
                    Result.success()
                }
                else -> {
                    Result.success(dbResponse.map())
                }
            }
        } catch(e: Exception) {
            Result.localError(e)
        }
    }

    open suspend fun saveLocalObject(databaseEntity: DatabaseEntity,
                                     saveToDatabase: suspend (DatabaseEntity) -> DatabaseEntity): Result<DatabaseEntity>{
        return try {
            val dbResponse = saveToDatabase.invoke(databaseEntity)
            Result.success(dbResponse)
        } catch(e: Exception) {
            Result.localError(e)
        }
    }

    open suspend fun saveLocalObjects(databaseEntities: List<DatabaseEntity>,
                                      saveToDatabase: suspend (List<DatabaseEntity>) -> List<DatabaseEntity>): Result<List<DomainEntity>>{
        return try {
            val dbResponse = saveToDatabase.invoke(databaseEntities)
                .map { databaseEntity ->
                    databaseEntity.map()
                }
            Result.success(dbResponse)
        } catch(e: Exception) {
            Result.localError(e)
        }
    }

}