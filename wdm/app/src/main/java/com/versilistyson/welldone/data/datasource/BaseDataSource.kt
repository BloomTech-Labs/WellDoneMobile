package com.versilistyson.welldone.data.datasource

import com.versilistyson.welldone.domain.common.Result
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>) : Result<T> {
        try {
            val response = call()
            if(response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body)
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Throwable) {
            return Result.Error(e)
        }
    }
}