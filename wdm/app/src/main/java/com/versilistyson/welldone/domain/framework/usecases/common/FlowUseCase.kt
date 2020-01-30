package com.versilistyson.welldone.domain.framework.usecases.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

@InternalCoroutinesApi
abstract class FlowUseCase<Type, in Params> where Type : Any {
    abstract suspend fun run(params: Params): Flow<Either<Failure, Type>>

    /*
        Invoke basically launches run inside a coroutine. Usually we'd want this
        scoped to the viewmodel's scope. Flow is being collected inside run and
        returned as live data
     */
    open suspend operator fun invoke(
        coroutineScope: CoroutineScope,
        params: Params
    ) : LiveData<Either<Failure, Type>> {
       val liveDataEither = coroutineScope.async {
           run(params).asLiveData(coroutineContext)
        }
        return liveDataEither.await()
    }
    class None
}