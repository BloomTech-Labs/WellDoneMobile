package com.versilistyson.welldone.presentation.ui.authentication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.versilistyson.welldone.R
import com.versilistyson.welldone.presentation.viewmodel.AuthSharedViewModel
import com.versilistyson.welldone.presentation.ui.authentication.fragment.SignInFragmentDirections
import kotlinx.android.synthetic.main.fragment_sign_in_screen.*
import javax.inject.Inject

class SignInFragment : Fragment() {

    private lateinit var action: NavDirections
    @Inject lateinit var authSharedViewModel: AuthSharedViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AuthenticationActivity).authComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authSharedViewModel.errorMessage.observe(
            viewLifecycleOwner,
            Observer {
                newErrorMessage ->
                Toast.makeText(this.context, newErrorMessage, Toast.LENGTH_SHORT).show()
            }
        )

        authSharedViewModel.authenticationResult.observe(
            viewLifecycleOwner,
            Observer { newAuthenticationState ->
                when (newAuthenticationState) {
                    AuthenticationState.SUCCESSFUL -> {
                        authSharedViewModel.resetAuthenticationState()
                        action =
                            SignInFragmentDirections.actionSignInScreenToDashboardActivity()
                        findNavController().navigate(action)
                    }
                    AuthenticationState.FAILED -> {
                        bttn_signIn.isEnabled = true
                        signInFragment_cv_authFailed.visibility = View.VISIBLE
                        cv_forSignInStatus.visibility = View.INVISIBLE
                        signIn_progressBar.animate().cancel()
                    }
                    AuthenticationState.ERROR -> {
                        bttn_signIn.isEnabled = true
                        authSharedViewModel.resetAuthenticationState()
                    }
                    AuthenticationState.PROCESSING -> {
                        bttn_signIn.isEnabled = false
                        cv_forSignInStatus.visibility = View.VISIBLE
                        signInFragment_cv_authFailed.visibility = View.INVISIBLE
                        signIn_progressBar.animate().start()
                    }
                    AuthenticationState.WAITING -> {
                        bttn_signIn.isEnabled = true
                        cv_forSignInStatus.visibility = View.INVISIBLE
                        signInFragment_cv_authFailed.visibility = View.INVISIBLE
                        signIn_progressBar.animate().cancel()
                    }
                }
            }
        )

        bttn_signIn.setOnClickListener {
            if (!signInFragment_et_email.text.isNullOrBlank() && !signInFragment_et_password.text.isNullOrBlank()) {
                val email = signInFragment_et_email.text.toString()
                val password = signInFragment_et_password.text.toString()
                authSharedViewModel.authenticateUser(email, password)
            }
        }
    }
}