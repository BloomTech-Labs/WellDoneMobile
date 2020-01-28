package com.versilistyson.welldone.domain.framework.repository

import com.dropbox.android.external.store4.StoreResponse
import com.versilistyson.welldone.data.db.user.UserDetailsData
import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {
//    fun fetchUserDetails(): Flow<Entity.UserDetails>
    fun fetchFreshUserDetails(): Flow<StoreResponse<UserDetailsData>>
    fun fetchUserDetails(): Flow<StoreResponse<UserDetailsData>>
}