package com.versilistyson.welldone.presentation.ui.dashboard

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView

import com.versilistyson.welldone.R
import com.versilistyson.welldone.presentation.viewmodel.DashboardViewmodel

class FullScreenMapFragment : Fragment() {

    private lateinit var viewModel: DashboardViewmodel
    private lateinit var fullScreenMap: GoogleMap
    private lateinit var fullScreenMapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.full_screen_map_fragment, container, false)
        viewModel = activity.let {
            val appContext = activity?.applicationContext as Application
            ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(appContext)
                .create(DashboardViewmodel::class.java)
        }
        fullScreenMapView = view.findViewById(R.id.fullscreen_map_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}