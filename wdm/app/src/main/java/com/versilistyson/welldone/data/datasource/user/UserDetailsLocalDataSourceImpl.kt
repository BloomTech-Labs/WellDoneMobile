package com.versilistyson.welldone.data.datasource.user

import com.versilistyson.welldone.data.db.user.UserDetailsDao
import com.versilistyson.welldone.data.db.user.UserDetailsData
import com.versilistyson.welldone.domain.framework.datasource.user.UserDetailsLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDetailsLocalDataSourceImpl @Inject constructor(userDetailsDao: UserDetailsDao): UserDetailsLocalDataSource {

    override fun getUserDetails(key: String): Flow<UserDetailsData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveUserDetails(key: String, userDetail: UserDetailsData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteUserDetails(userId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
