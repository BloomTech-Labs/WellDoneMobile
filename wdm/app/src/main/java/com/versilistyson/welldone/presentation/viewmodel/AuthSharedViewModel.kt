package com.versilistyson.welldone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.usecases.user.SignInUseCase
import javax.inject.Inject

//with a matching scope in the component, this will determine that the component does not exist outside the lifetime of the component

class AuthSharedViewModel @Inject constructor(private val signInUseCase: SignInUseCase) :
    ViewModel() {

    sealed class AuthenticationResult {
        object LOADING : AuthenticationResult()
        object EMPTY : AuthenticationResult()
        object LOGIN_FAILED : AuthenticationResult()
        data class SUCCESS(val user: Entity.UserDetails)
    }

    private val _errorCode: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    private val _errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _authenticationResult: MutableLiveData<AuthenticationResult> = MutableLiveData(
        AuthenticationResult.LOADING
    )
    val authenticationResult: LiveData<AuthenticationResult>
        get() = _authenticationResult

    fun signInUser(email: String, password: String) =
        signInUseCase.invoke(viewModelScope, SignInUseCase.Params(email, password)) {
            it.either(::onFailure,::onSuccess)
        }

    private fun onSuccess(authenticatedUser: Entity.UserDetails) {
    }

    private fun onFailure(failure: Failure) {
       when(failure) {
           is Failure.NetworkConnection -> {}
           is SignInUseCase.InvalidSignInCredentials-> {}
           is Failure.ServerError -> {}
           is Failure.EmptyResponse -> {}
       }
    }

    /*private val _uid: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val uid: LiveData<Int>
        get() = _uid

    private val _authToken: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val authToken: LiveData<String>
        get() = _authToken

    fun resetAuthenticationState() {
        _authenticationState.postValue(AuthenticationState.WAITING)
    }*/
}