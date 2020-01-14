package com.versilistyson.welldone.ui.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.versilistyson.MyApplication
import com.versilistyson.welldone.repository.AuthenticationRepository
import javax.inject.Inject

class AuthViewModelFactory @Inject constructor (private val authRepository: AuthenticationRepository, private val application: MyApplication)
                                : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AuthSharedViewModel::class.java)) {
            AuthSharedViewModel(authRepository, application) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}