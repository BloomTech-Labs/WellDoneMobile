package com.versilistyson.welldone.domain.framework.datasource.user

import androidx.lifecycle.LiveData
import com.versilistyson.welldone.data.api.user.UserDetailsApi
import com.versilistyson.welldone.data.db.user.UserDetailsData
import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource

interface UserDetailsLocalDataSource: BaseDataSource {
    fun getUserDetails(userId: Long)
    fun storeUserDetails(
        userId: Long,
        firstName: String,
        lastName: String,
        email: String = "",
        phone: String = ""
    ): LiveData<UserDetailsData?>

    fun deleteUserDetails(userId: Long)
}