package com.versilistyson.welldone.domain.framework.usecases.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

@InternalCoroutinesApi
abstract class FlowUseCase<Type, in Params> where Type : Any {
    abstract suspend fun run(params: Params): Flow<Either<Failure, Type>>

    open suspend operator fun invoke(
        coroutineScope: CoroutineScope,
        params: Params
    ) : LiveData<Either<Failure,Type>> {
       val liveDataEither = coroutineScope.async {
           return@async run(params).asLiveData()
        }
        return liveDataEither.await()
    }
    class None
}