package com.versilistyson.welldone.presentation.ui.authentication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.versilistyson.welldone.R
import com.versilistyson.welldone.presentation.viewmodel.AuthSharedViewModel

class SplashScreenFragment : Fragment() {

    /*@Inject*/ lateinit var authSharedViewModel: AuthSharedViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        (activity as AuthenticationActivity).authComponent.inject(this)
        authSharedViewModel = ViewModelProvider(activity!!)[AuthSharedViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }
    
}
