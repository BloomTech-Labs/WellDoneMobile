package com.versilistyson.welldone.ui.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.versilistyson.welldone.R
import javax.inject.Inject

class AuthenticationActivity : AppCompatActivity() {

    private val host by lazy {
        NavHostFragment.create(R.navigation.auth_nav_graph)
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var authViewModel: AuthSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

//        authViewModel = ViewModelProvider
//            .AndroidViewModelFactory
//            .getInstance(application)
//            .create(AuthSharedViewModel::class.java)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, host)
            .setPrimaryNavigationFragment(host).commit()

    }
}
