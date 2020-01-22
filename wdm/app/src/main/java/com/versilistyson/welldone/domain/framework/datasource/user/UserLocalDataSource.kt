package com.versilistyson.welldone.domain.framework.datasource.user

import com.versilistyson.welldone.domain.framework.datasource.BaseDataSource

interface UserLocalDataSource: BaseDataSource {
    fun storeToken()
    fun getToken()
    fun storeUserDetails(userId: Long, firstName: String, lastName: String)
}