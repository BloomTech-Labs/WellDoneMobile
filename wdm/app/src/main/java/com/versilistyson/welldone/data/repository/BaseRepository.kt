package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.data.util.Mappable
import com.versilistyson.welldone.domain.common.Result
import retrofit2.Response

abstract class BaseRepository<T: Mappable<out L>, out L> {

    open suspend fun fetchNetworkObjects(call: suspend ()-> Response<List<T>>): Result<List<L>> {
        try {
            val response = call()
            val mappedResponse = mutableListOf<L>()
            return if (response.isSuccessful) {
                when (response.body()) {
                    null -> {
                        Result.success(code = response.code())
                    }
                    else -> {
                        for (obj in response.body()!!) {
                            mappedResponse.add(obj.map())
                        }
                        Result.success(mappedResponse, response.code())
                    }
                }
            } else {
                Result.networkError(code = response.code())
            }
        } catch (e: Exception) {
            return Result.networkError(e)
        }
    }

    open suspend fun fetchNetworkObject(call: ()-> Response<T>): Result<L>{
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
}