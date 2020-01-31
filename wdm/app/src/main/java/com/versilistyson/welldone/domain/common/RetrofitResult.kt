package com.versilistyson.welldone.domain.common

sealed class RetrofitResult<T> {
    data class Data<T>(val data: T?): RetrofitResult<T>()
    data class Error<T>(val errorMessage: String?, val errorCode: Int?):RetrofitResult<T>()
}