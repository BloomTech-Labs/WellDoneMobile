package com.versilistyson.welldone.ui.dashboard.fragment

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.MapView

import com.versilistyson.welldone.R
import com.versilistyson.welldone.ui.dashboard.DashboardViewmodel

class FullScreenMapFragment : Fragment() {

    private lateinit var sharedViewModel: DashboardViewmodel
    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.full_screen_map_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel = activity.let {
            val appContext = activity?.applicationContext as Application
            ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(appContext)
                .create(DashboardViewmodel::class.java)
        }
        // TODO: Use the ViewModel
    }

}
