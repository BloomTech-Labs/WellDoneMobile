package com.versilistyson.welldone.domain.framework.datasource

//abstract class BaseDataSource {
//
//    protected suspend fun <T> getResult(call: suspend () -> Response<T>) : Result<T> {
//        try {
//            val response = call()
//            if(response.isSuccessful) {
//                val body = response.body()
//                if (body != null) return Result.success(body)
//            }
//            return Result.error("${response.code()} ${response.message()}")
//        } catch (e: Exception) {
//            return Result.error(e.message ?: e.toString())
//        }
//    }
//}