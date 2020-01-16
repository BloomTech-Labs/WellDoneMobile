package com.versilistyson.welldone.data.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.versilistyson.welldone.domain.common.Result
import kotlinx.coroutines.Dispatchers

fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
) : LiveData<Result<T>> = liveData(Dispatchers.IO) {
    emit(Result.loading<T>())
    val source: LiveData<Result<T>> = databaseQuery.invoke().map { Result.success(it) }
    emitSource(source)

    val responseStatus = networkCall.invoke()
    if (responseStatus.status == Result.Status.SUCCESS) {
        saveCallResult(responseStatus.data!!)
    }
    else if (responseStatus.status == Result.Status.ERROR) {
        emit(Result.error<T>(responseStatus.message!!))
        emitSource(source)
    }
}