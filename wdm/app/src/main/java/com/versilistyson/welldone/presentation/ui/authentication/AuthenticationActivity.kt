package com.versilistyson.welldone.presentation.ui.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.versilistyson.welldone.R
import com.versilistyson.welldone.presentation.MyApplication
import com.versilistyson.welldone.presentation.di.auth.component.AuthenticationComponent
import com.versilistyson.welldone.presentation.viewmodel.AuthSharedViewModel
import com.versilistyson.welldone.presentation.di.viewmodel_util.ViewModelProviderFactory
import javax.inject.Inject

class AuthenticationActivity : AppCompatActivity() {

    private val host by lazy {
        NavHostFragment.create(R.navigation.auth_nav_graph)
    }

    lateinit var authenticationComponent: AuthenticationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

//        authenticationComponent = (applicationContext as MyApplication).appComponent.authComponent()
//        authenticationComponent.inject(this)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, host)
            .setPrimaryNavigationFragment(host).commit()
    }
}
