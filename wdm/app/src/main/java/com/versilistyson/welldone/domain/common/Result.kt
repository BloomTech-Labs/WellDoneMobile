package com.versilistyson.welldone.domain.common

//all this class does is allows us to re-wrap the response information because it needs to be mapped
data class Result<out T>(val status: Status, val data: T? = null, val responseCode: Int? = null, val exception: Exception?) {

    enum class Status {
        SUCCESS,
        NETWORK_ERROR,
        LOCAL_ERROR
    }

    companion object {
        fun <T> success(data: T? = null, code: Int? = null): Result<T> = Result(Status.SUCCESS, data, code, null)

        fun <T> networkError(exception: Exception? = null, code: Int? = null): Result<T> =
            Result(Status.NETWORK_ERROR, null, code, exception)

        fun <T> localError(exception: Exception? = null): Result<T> =
            Result(Status.LOCAL_ERROR, null, null, exception)
    }
}