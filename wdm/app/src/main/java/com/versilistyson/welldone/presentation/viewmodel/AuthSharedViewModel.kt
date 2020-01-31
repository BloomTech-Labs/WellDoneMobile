package com.versilistyson.welldone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.common.ResponseResult
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.usecases.user.SaveUserIdUseCase
import com.versilistyson.welldone.domain.framework.usecases.user.SaveUserTokenUseCase
import com.versilistyson.welldone.domain.framework.usecases.user.SignInUseCase
import javax.inject.Inject

//with a matching scope in the component, this will determine that the component does not exist outside the lifetime of the component

class AuthSharedViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val saveUserIdUseCase: SaveUserIdUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase
) :
    ViewModel() {

    /*sealed class AuthenticationResult {
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
*/
    fun storeUserId(userId: Long) = saveUserIdUseCase.invoke(viewModelScope, SaveUserIdUseCase.Params(userId)) { result ->
        result.either(::onSaveAuthSharedPrefFailure, ::onSaveAuthSharedPrefSuccess)

    }

    fun storeUserToken(token: String) =
        saveUserTokenUseCase.invoke(viewModelScope, SaveUserTokenUseCase.Params(token)) { result ->
            result.either(::onSaveAuthSharedPrefFailure, ::onSaveAuthSharedPrefSuccess)
        }

    private fun onSaveAuthSharedPrefFailure(failure: Failure) {

    }

    private fun onSaveAuthSharedPrefSuccess(savedSuccessfully: Boolean) {

    }

    fun signInUser(email: String, password: String) =
        signInUseCase.invoke(viewModelScope, SignInUseCase.Params(email, password)) {
            it.either(::onSignInFailure, ::onSignInSuccess)
        }

    private fun onSignInSuccess(result: ResponseResult<Entity.AuthenticationDetails?>) {
        when (result) {
            is ResponseResult.Loading -> {
                TODO()
            }
            is ResponseResult.Data -> {
                if(result.value != null) {
                    storeUserId(result.value.userId)
                    storeUserToken(result.value.token)
                }
            }
        }
    }

    private fun onSignInFailure(failureOrLoading: Failure) {
        when (failureOrLoading) {
            is Failure.NetworkConnection -> {
            }
            is SignInUseCase.InvalidSignInCredentials -> {
            }
            is Failure.ServerFailure -> {
            }
            is Failure.EmptyResponse -> {
            }
        }
    }
}