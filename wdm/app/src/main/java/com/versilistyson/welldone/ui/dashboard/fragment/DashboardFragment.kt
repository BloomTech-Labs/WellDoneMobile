package com.versilistyson.welldone.ui.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.versilistyson.welldone.R
import com.versilistyson.welldone.ui.dashboard.DashboardViewmodel

class DashboardFragment : Fragment(), OnMapReadyCallback {

    private lateinit var viewmodel: DashboardViewmodel
    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = activity!!.supportFragmentManager
            .findFragmentById(R.id.frag_map) as SupportMapFragment

        mapFragment.getMapAsync(this)

        viewmodel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
            .create(DashboardViewmodel::class.java)

        viewmodel.pumpLiveData.observe(viewLifecycleOwner, Observer{
            if(it.isSuccessful){

            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
