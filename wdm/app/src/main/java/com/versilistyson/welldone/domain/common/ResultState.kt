package com.versilistyson.welldone.domain.common

sealed class ResultState<T> {
    // Data state showing
    data class Loading<T>(val data: T) : ResultState<T>()
    //
    data class Success<T>(val data: T) : ResultState<T>()
    //
    data class Error<T>(val throwable: Throwable, val data: T?) : ResultState<T>()
}