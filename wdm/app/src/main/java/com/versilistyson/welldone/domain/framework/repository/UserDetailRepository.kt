package com.versilistyson.welldone.domain.framework.repository

import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {
    fun fetchUserDetails(): Flow<Entity.UserDetails>
    fun fetchFreshUserDetails(): Flow<Entity.UserDetails>
    fun fetchLocalUserDetails(): Flow<Entity.UserDetails>
}