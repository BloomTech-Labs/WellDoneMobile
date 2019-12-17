package com.versilistyson.welldone.ui.authentication.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.versilistyson.welldone.R
import com.versilistyson.welldone.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_sign_in_screen.*

class SignInScreen : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_sign_in.setOnClickListener {
            val intent = Intent(context, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}
