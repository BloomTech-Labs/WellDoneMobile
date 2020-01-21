package com.versilistyson.welldone.data.mappers

import com.versilistyson.welldone.data.api.UserApi
import com.versilistyson.welldone.domain.common.Mapper
import com.versilistyson.welldone.domain.framework.entity.Entity

class AuthResponseToAuthUserMapper: Mapper<UserApi.Dto.AuthenticationResponse, Entity.AuthenticatedUser>() {

    override fun mapFrom(from: UserApi.Dto.AuthenticationResponse): Entity.AuthenticatedUser {
        return Entity.AuthenticatedUser(
            from.authToken,
            from.userId
        )
    }
}