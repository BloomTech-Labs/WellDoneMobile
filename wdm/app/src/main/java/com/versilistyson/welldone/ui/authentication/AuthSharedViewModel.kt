package com.versilistyson.welldone.ui.authentication

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.versilistyson.welldone.MyApplication
import com.versilistyson.welldone.data.remote.dto.AuthenticationRequest
import com.versilistyson.welldone.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

//with a matching scope in the component, this will determine that the component does not exist outside the lifetime of the component

class AuthSharedViewModel @Inject constructor(private val application: Application,
                                              private val authRepository: AuthenticationRepository): ViewModel() {

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
            authRepository.signIn(
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
            (application as MyApplication).saveToken(resultBody.authToken)
            _authenticationState.postValue(AuthenticationState.SUCCESSFUL)
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