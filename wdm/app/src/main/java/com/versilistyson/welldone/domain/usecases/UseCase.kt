package com.versilistyson.welldone.domain.usecases

import kotlinx.coroutines.Dispatchers

/*
    UseCase is called by the viewmodel. UseCases usually wrap repositories and call a specific
    method, and will determine what result to provide to the viewmodel. This keeps business logic
    out of the viewmodel class.
 */
abstract class UseCase {

    private val mainDispatcher = Dispatchers.Main
    private val backgroundDispatcher = Dispatchers.Default


}