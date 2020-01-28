package com.versilistyson.welldone.data.datasource.user

import android.content.Context
import com.versilistyson.welldone.domain.framework.datasource.user.UserAuthenticationLocalDataSource

class UserAuthenticationLocalDataSourceImpl(private val context: Context): UserAuthenticationLocalDataSource {

    companion object SharedPreferenceKey {
        const val USER_ID = "com.versilistyson.welldone.USER_ID"
        const val USER_TOKEN = "com.versilistyson.welldone.USER_TOKEN"
    }

    override fun saveToken(token: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveId(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteUserId() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteToken(userId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}