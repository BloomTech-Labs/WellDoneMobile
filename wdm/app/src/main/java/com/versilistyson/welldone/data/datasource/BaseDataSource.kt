package com.versilistyson.welldone.data.datasource

import androidx.lifecycle.LiveData
import com.versilistyson.welldone.domain.common.Result
import retrofit2.Response
import java.lang.Exception

abstract class BaseDataSource {

    protected suspend fun <T> getResultByNetwork(call: suspend () -> Response<T>) : Result<T> {
        try {
            val response = call()
            if(response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return Result.error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return Result.error(e.message ?: e.toString())
        }
    }

    abstract fun <T> getResultByDatabase(call: suspend() -> LiveData<T>): LiveData<Result<T>>

    abstract fun <T> saveResultToDatabase(call: suspend(T) -> Result<T>)


}