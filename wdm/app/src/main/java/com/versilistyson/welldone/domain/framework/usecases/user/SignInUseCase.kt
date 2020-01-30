package com.versilistyson.welldone.domain.framework.usecases.user

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.UserDetailRepository

class SignInUseCase(private val userRepository: UserDetailRepository):
    UseCase<Entity.UserDetails, SignInUseCase.Params>() {


    override suspend fun run(params: Params): Either<Failure, Entity.UserDetails> {
        TODO()
    }

    data class Params(val email: String, val password: String)

    data class InvalidSignInCredentials(val e: Exception = Exception(Exception("Bad Authentication"))): Failure.FeatureFailure(e)

}

//try {
//    val authenticationResponse = userRepository.signInUser(params.email, params.password)
//    return if (authenticationResponse.isSuccessful) {
//        when (authenticationResponse.body()) {
//            null -> {
//                Either.Left(Failure.EmptyResponse)
//            }
//            else -> {
//                Either.Right(authenticationResponse.body()!!)
//            }
//        }
//    } else {
//        return when(authenticationResponse.code()) {
//            401 -> Either.Left(SignInUseCase.InvalidSignInCredentials())
//            else -> Either.Left(Failure.ServerError)
//        }
//    }
//} catch (exception: Exception) {
//    return Either.Left(Failure.ServerError)
//}