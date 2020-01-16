package com.versilistyson.welldone.data.datasource.user

import com.versilistyson.welldone.domain.entity.Entity

interface IUserApiDataSource {
    suspend fun signInUser(email: String, password: String) : Entity.AuthenticatedUser
}