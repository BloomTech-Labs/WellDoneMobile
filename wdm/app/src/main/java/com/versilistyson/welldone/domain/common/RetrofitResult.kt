package com.versilistyson.welldone.domain.common

sealed class RetrofitResult<T>: Result() {
    data class Data<T>(val data: T?): RetrofitResult<T>()
    data class Error<T>(val errorMessage: String? = null, val errorCode: Int? = null):RetrofitResult<T>()
}