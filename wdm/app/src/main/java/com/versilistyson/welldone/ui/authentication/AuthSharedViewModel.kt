package com.versilistyson.welldone.ui.authentication

import android.app.Application
import androidx.lifecycle.*
import com.versilistyson.welldone.data.remote.dto.AuthenticationRequest
import com.versilistyson.welldone.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AuthSharedViewModel(application: Application) : AndroidViewModel(application) {

    private val authenticationRepository = AuthenticationRepository()

    private val _uid: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val uid: LiveData<Int>
        get() = _uid

    private val _authToken: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val authToken: LiveData<String>
        get() = _authToken

    fun authenticateUser(email: String, password: String) = viewModelScope.launch {
        val result = async {
            authenticationRepository.signIn(
                AuthenticationRequest(
                    email,
                    password
                )
            )
        }.await()
        if (result.isSuccessful && result.body() != null) {
            val resultBody = result.body()
            _uid.postValue(resultBody!!.userId)
            _authToken.postValue(resultBody.authToken)
        }
    }
}