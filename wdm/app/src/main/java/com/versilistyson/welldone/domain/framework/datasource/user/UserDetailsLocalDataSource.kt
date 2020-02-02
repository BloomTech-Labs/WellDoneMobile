package com.versilistyson.welldone.domain.framework.datasource.user

import com.versilistyson.welldone.data.db.user.UserDetailsData
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource
import kotlinx.coroutines.flow.Flow

interface UserDetailsLocalDataSource: BaseDataSource {

    fun getUserDetails(key: String): Flow<UserDetailsData>

    suspend fun saveUserDetails(
        key: String,
        userDetail: UserDetailsData
    )

    fun deleteUserDetails(userId: Long)
}