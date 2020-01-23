package com.versilistyson.welldone.data.repository

import androidx.lifecycle.LiveData
import com.versilistyson.welldone.data.util.Mappable
import com.versilistyson.welldone.domain.common.Result
import retrofit2.Response

abstract class BaseRepository<RemoteEntity : Mappable<out DatabaseEntity>, DatabaseEntity : Mappable<out DomainEntity>, out DomainEntity> {

    open suspend fun fetchNetworkObjects(
        getFromNetwork: suspend () -> Response<List<RemoteEntity>>,
        saveToDatabase: suspend (List<DatabaseEntity>) -> List<DatabaseEntity>
    ): Result<List<DomainEntity>> {
        try {
            val remoteResponse = getFromNetwork.invoke()
            return if (remoteResponse.isSuccessful) {
                when (remoteResponse.body()) {
                    null -> {
                        Result.success(code = remoteResponse.code())
                    }
                    else -> {

                        val dbResponse = saveToDatabase.invoke(
                            remoteResponse.body()!!.map { remoteEntity ->
                                remoteEntity.map()
                            }
                        )
                        .map { databaseEntity ->
                            databaseEntity.map()
                        }
                        Result.success(dbResponse, code = remoteResponse.code())
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
        getFromNetwork: suspend () -> Response<RemoteEntity>,
        saveToDatabase: suspend (DatabaseEntity) -> DatabaseEntity
    ): Result<DomainEntity> {
        return try {
            val remoteResponse = getFromNetwork.invoke()
            if (remoteResponse.isSuccessful) {
                when (remoteResponse.body()) {
                    null -> {
                        Result.success(null, remoteResponse.code())
                    }
                    else -> {
                        val mappedResponse = remoteResponse.body()!!.map()
                        val databaseResponse = saveToDatabase.invoke(mappedResponse)
                        Result.success(databaseResponse.map(), remoteResponse.code())
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

    open suspend fun fetchLocalObject(getFromDatabase: suspend () -> List<DatabaseEntity>): Result<List<DomainEntity>> {
        return try {
            when(val dbResponse = getFromDatabase.invoke()) {
                null -> {
                    Result.success()
                }
                else -> {
                    Result.success(dbResponse.map { dbEntity -> dbEntity.map() })
                }
            }
        } catch(e: java.lang.Exception) {
            Result.localError(e)
        }
    }


}