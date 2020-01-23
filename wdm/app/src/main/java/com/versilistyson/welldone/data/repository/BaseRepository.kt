package com.versilistyson.welldone.data.repository

import androidx.lifecycle.LiveData
import com.versilistyson.welldone.data.util.Mappable
import com.versilistyson.welldone.domain.common.Result
import retrofit2.Response

abstract class BaseRepository<RemoteEntity: Mappable<out DatabaseEntity>, DatabaseEntity: Mappable<out DomainEntity>, out DomainEntity> {

    open suspend fun fetchNetworkObjects(
        getFromNetwork: suspend ()-> Response<List<RemoteEntity>>,
        saveToDatabase: suspend(List<DatabaseEntity>) -> LiveData<List<DatabaseEntity>>
    ): Result<LiveData<List<DatabaseEntity>>> {
        try {
            val response = getFromNetwork()
            val mappedResponse = mutableListOf<DatabaseEntity>()
            return if (response.isSuccessful) {
                when (response.body()) {
                    null -> {
                        Result.success(code = response.code())
                    }
                    else -> {
                        for (obj in response.body()!!) {
                            mappedResponse.add(obj.map())
                        }
                        val dataBaseResponse = saveToDatabase.invoke(mappedResponse)
                        Result.success(dataBaseResponse, response.code())
                    }
                }
            } else {
                Result.networkError(code = response.code())
            }
        } catch (e: Exception) {
            return Result.networkError(e)
        }
    }

    open suspend fun fetchNetworkObject(call: suspend ()-> Response<RemoteEntity>): Result<DomainEntity>{
        try {
            val response = call()
            return if (response.isSuccessful) {
                when (response.body()) {
                    null -> {
                        Result.success(code = response.code())
                    }
                    else -> {
                        Result.success(response.body()!!.map(), response.code())
                    }
                }
            } else {
                Result.networkError(code = response.code())
            }
        } catch (e: Exception) {
            return Result.networkError(e)
        }
    }

    open suspend fun fetchDatabaseObject
}