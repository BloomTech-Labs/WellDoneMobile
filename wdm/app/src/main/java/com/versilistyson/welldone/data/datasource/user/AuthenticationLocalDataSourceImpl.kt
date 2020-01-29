package com.versilistyson.welldone.data.datasource.user

import android.content.Context
import com.versilistyson.welldone.data.util.SharedPreferenceKeys.Authentication

import com.versilistyson.welldone.domain.framework.datasource.user.AuthenticationLocalDataSource

class AuthenticationLocalDataSourceImpl(context: Context): AuthenticationLocalDataSource {

    private val sharedPref = context.getSharedPreferences(Authentication.KEY, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    override suspend fun saveToken(token: String): Boolean {
       return editor.putString(Authentication.USER_TOKEN, token).commit()
    }

    override suspend fun saveId(userId: Long): Boolean {
        return editor.putLong(Authentication.USER_TOKEN, userId).commit()
    }

    override suspend fun deleteUserId(): Boolean {
        return editor.remove(Authentication.USER_ID).commit()
    }

    override suspend fun deleteToken(): Boolean {
        return editor.remove(Authentication.USER_TOKEN).commit()
    }

    override fun getToken(): String? {
        return sharedPref.getString(Authentication.USER_TOKEN, null)
    }

    override fun getUserId(): Long {
        return sharedPref.getLong(Authentication.USER_ID, 0)
    }
}