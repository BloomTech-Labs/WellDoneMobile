package com.versilistyson.welldone.domain.framework.usecases.user

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.UserDetailRepository
import com.versilistyson.welldone.domain.framework.usecases.UseCase
import kotlinx.coroutines.CoroutineScope

class GetUserDetailsUseCase(userDetailsRepository: UserDetailRepository): UseCase<Failure, Entity.UserDetails>() {

    override suspend fun run(params: Entity.UserDetails): Either<Failure, Failure> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invoke(
        scope: CoroutineScope,
        params: Entity.UserDetails,
        onResult: (Either<Failure, Failure>) -> Unit
    ) {
        super.invoke(scope, params, onResult)
    }
}