package com.versilistyson.welldone.domain.common

//https://github.com/GlueHome/common-android/blob/master/domain/src/main/kotlin/com/gluehome/common/domain/exceptions/Failure.kt

sealed class Failure(val exception: Exception = Exception("Failure")): Result() {
    object EmptyResponse: Failure()
    object NetworkConnection: Failure()
    class ServerFailure(val serverException: Exception = Exception("Server Failure")): Failure(serverException)
    class CacheFailure(val cacheException: Exception = Exception("Cache Failure")): Failure(cacheException)
    class PersisterFailure(val persisterFailure: Exception = Exception("Persister Failure")): Failure()

    abstract class FeatureFailure(featureException: Exception = Exception("Feature failure")) : Failure(featureException)
    override fun equals(other: Any?): Boolean {
        return other is Failure
    }
}