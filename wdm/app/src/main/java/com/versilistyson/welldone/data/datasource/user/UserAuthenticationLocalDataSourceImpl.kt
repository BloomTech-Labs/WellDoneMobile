package com.versilistyson.welldone.data.datasource.user

import android.content.Context
import com.versilistyson.welldone.data.util.SharedPreferenceKeys.Authentication

import com.versilistyson.welldone.domain.framework.datasource.user.UserAuthenticationLocalDataSource

class UserAuthenticationLocalDataSourceImpl(context: Context): UserAuthenticationLocalDataSource {

    private val sharedPref = context.getSharedPreferences(Authentication.KEY, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    override suspend fun saveToken(token: String): Boolean {
       return editor.putString(Authentication.USER_TOKEN, token).commit()
    }

    override suspend fun saveId(id: String): Boolean {
        return editor.putString(Authentication.USER_ID, id).commit()
    }

    override suspend fun deleteUserId(): Boolean {
        return editor.remove(Authentication.USER_ID).commit()
    }

    override suspend fun deleteToken(userId: String): Boolean {
        return editor.remove(Authentication.USER_TOKEN).commit()
    }
}