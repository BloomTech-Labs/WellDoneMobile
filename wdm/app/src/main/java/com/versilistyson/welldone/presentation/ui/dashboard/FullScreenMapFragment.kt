package com.versilistyson.welldone.presentation.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView

import com.versilistyson.welldone.R
import com.versilistyson.welldone.presentation.viewmodel.FullScreenMapViewModel
import javax.inject.Inject

class FullScreenMapFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var fullScreenMapViewModel: FullScreenMapViewModel
    private lateinit var fullScreenMap: GoogleMap
    private lateinit var fullScreenMapView: MapView

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.full_screen_map_fragment, container, false)
        fullScreenMapView = view.findViewById(R.id.fullscreen_map_view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fullScreenMapViewModel = viewModelFactory.create(FullScreenMapViewModel::class.java)
    }
}