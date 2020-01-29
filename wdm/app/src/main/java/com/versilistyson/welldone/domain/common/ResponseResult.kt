package com.versilistyson.welldone.domain.common

import com.dropbox.android.external.store4.ResponseOrigin

//all this class does is allows us to re-wrap the response information because it needs to be mapped
sealed class ResponseResult<T> {
    data class Loading<T>(val origin: ResponseOrigin) : ResponseResult<T>()
    data class Data<T>(val value: T, val origin: ResponseOrigin) : ResponseResult<T>()
}