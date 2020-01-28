package com.versilistyson.welldone.domain.common

//https://github.com/GlueHome/common-android/blob/master/domain/src/main/kotlin/com/gluehome/common/domain/exceptions/Failure.kt
sealed class Failure(val exception: Exception = Exception("Failure")) {
    object None: Failure()
    object EmptyResponse: Failure()
    object NetworkConnection: Failure()
    object ServerError: Failure()
    object CurrentlyLoading: Failure()

    abstract class FeatureFailure(featureException: Exception = Exception("Feature failure")) : Failure(featureException)
    override fun equals(other: Any?): Boolean {
        return other is Failure
    }
}