package com.versilistyson.welldone.ui.authentication.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.versilistyson.welldone.R
import com.versilistyson.welldone.ui.authentication.AuthSharedViewModel
import com.versilistyson.welldone.ui.authentication.AuthenticationActivity
import javax.inject.Inject

class SplashScreenFragment : Fragment() {

    @Inject lateinit var authSharedViewModel: AuthSharedViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AuthenticationActivity).authComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }
    
}
