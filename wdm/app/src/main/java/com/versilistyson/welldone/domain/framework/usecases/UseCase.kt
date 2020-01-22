package com.versilistyson.welldone.domain.framework.usecases

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import kotlinx.coroutines.*

/*
    UseCase is called by the viewmodel. UseCases usually wrap repositories and call a specific
    method, and will determine what result to provide to the viewmodel. This keeps business logic
    out of the viewmodel class.
 */
abstract class UseCase<out Type, in Params> where Type: Any {

    var NETWORK_TIMEOUT = 3000L

    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        val backgroundJob = scope.async { run(params) }
        scope.launch {
            onResult(
                withTimeout(NETWORK_TIMEOUT){
                    backgroundJob.await()
                }
            )
        }
    }

    class NoParams
}