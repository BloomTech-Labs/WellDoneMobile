package com.versilistyson.welldone.domain.framework.threads

import kotlin.coroutines.CoroutineContext

interface CoroutineThreads {
    fun ui(): CoroutineContext
    fun io(): CoroutineContext
}