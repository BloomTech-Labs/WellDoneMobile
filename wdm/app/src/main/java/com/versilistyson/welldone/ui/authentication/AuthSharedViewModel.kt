package com.versilistyson.welldone.ui.authentication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.versilistyson.MyApplication
import com.versilistyson.welldone.data.remote.dto.AuthenticationRequest
import com.versilistyson.welldone.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class AuthSharedViewModel(application: Application) : AndroidViewModel(application) {

    private val authenticationRepository = AuthenticationRepository()

    private val _errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val errorMessage: LiveData<String>
    get() = _errorMessage

    private val _authenticationState: MutableLiveData<AuthenticationState> = MutableLiveData(AuthenticationState.WAITING)
    val authenticationState: LiveData<AuthenticationState>
    get() = _authenticationState

    private val _uid: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val uid: LiveData<Int>
        get() = _uid

    private val _authToken: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val authToken: LiveData<String>
        get() = _authToken

    fun authenticateUser(email: String, password: String) = viewModelScope.launch {
        _authenticationState.postValue(AuthenticationState.PROCESSING)
        val result = withContext(Dispatchers.IO) {
            authenticationRepository.signIn(
                AuthenticationRequest(
                    email,
                    password
                )
            )
        }
        if (result.isSuccessful && result.body() != null) {
            val resultBody = result.body()
            _uid.postValue(resultBody!!.userId)
            _authToken.postValue(resultBody.authToken)
            //save the token in shared preferences
            getApplication<MyApplication>().saveToken(resultBody.authToken)
            _authenticationState.postValue(AuthenticationState.SUCCESFUL)
        } else {
            try {
                _authenticationState.postValue(AuthenticationState.FAILED)
                val errorMessage = result.message()
                _errorMessage.postValue(errorMessage)
            } catch (e: IOException) {

            }
        }
    }

    fun resetAuthenticationState() {
        _authenticationState.postValue(AuthenticationState.WAITING)
    }
}