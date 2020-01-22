package com.versilistyson.welldone.domain.framework.usecases.user

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.repository.UserRepository
import com.versilistyson.welldone.domain.framework.usecases.UseCase
import com.versilistyson.welldone.domain.framework.entity.Entity
import java.lang.Exception

class SignInUseCase(private val userRepository: UserRepository) :
    UseCase<Entity.AuthenticatedUser, SignInUseCase.Params, SignInUseCase.SignInFailure>() {


    override suspend fun run(params: Params): Either<SignInFailure, Entity.AuthenticatedUser> {
        try {
            val authenticationResponse = userRepository.signInUser(params.email, params.password)
            return if (authenticationResponse.isSuccessful) {
                when (authenticationResponse.body()) {
                    null -> {
                        Either.Left(SignInFailure.EmptyResponse())
                    }
                    else -> {
                        Either.Right(authenticationResponse.body()!!)
                    }
                }
            } else {
                return Either.Left(
                    SignInFailure.UnsuccessfulAuthenticationResponse(authenticationResponse.message(), authenticationResponse.code())
                )
            }
        } catch (exception: Exception) {
            return Either.Left(SignInFailure.GenericFailure(exception))
        }
    }

    data class Params(val email: String, val password: String)

    sealed class SignInFailure(
        val failureType: Failure,
        val errorMessage: String? = "",
        val errorCode: Int? = 0,
        val exc: Exception = Exception("Sign In Failure")
    ) : Failure.FeatureFailure(exc) {

        data class GenericFailure(val e: Exception): SignInFailure(failureType = None, exc = e)
        data class EmptyResponse(val e: Exception = Exception("Sign In Failure: Empty Response Body")): SignInFailure(failureType = EmptyResponse, exc = e)
        data class UnsuccessfulAuthenticationResponse(
            val eMessage: String?,
            val eCode: Int?,
            val e: Exception = Exception("Sign In Failure: Unsuccessful Authentication Response")
        ) : SignInFailure(ServerError, eMessage, eCode, e)

    }

}