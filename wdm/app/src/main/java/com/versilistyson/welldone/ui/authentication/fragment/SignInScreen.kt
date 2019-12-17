package com.versilistyson.welldone.ui.authentication.fragment


import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections

import com.versilistyson.welldone.R
import com.versilistyson.welldone.ui.authentication.AuthSharedViewModel
import com.versilistyson.welldone.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_sign_in_screen.*
import kotlinx.coroutines.*

class SignInScreen : Fragment() {

    private lateinit var action: NavDirections
    private lateinit var authViewModel: AuthSharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel = activity.let {
            val appContext = activity?.applicationContext as Application
            ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(appContext)
                .create(AuthSharedViewModel::class.java)
        }

        bttn_signIn.setOnClickListener {
            if(!signInFragment_et_email.text.isNullOrBlank() && !signInFragment_et_password.text.isNullOrBlank()) {
                val email = signInFragment_et_email.text.toString()
                val password = signInFragment_et_password.text.toString()
                authViewModel.authenticateUser(email, password).invokeOnCompletion {
                   Toast.makeText(this.context,"token: ${authViewModel.authToken.value} id: ${authViewModel.uid.value}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
