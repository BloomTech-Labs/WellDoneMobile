package com.versilistyson.welldone.ui.dashboard.fragment

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.versilistyson.welldone.R
import com.versilistyson.welldone.adapter.SensorStatusListAdapter
import com.versilistyson.welldone.data.remote.dto.SensorRecentResponse
import com.versilistyson.welldone.ui.dashboard.DashboardViewmodel
import com.versilistyson.welldone.util.MAPVIEW_BUNDLE_KEY
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.marker_info_window_layout.*
import kotlinx.android.synthetic.main.marker_info_window_layout.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var viewmodel: DashboardViewmodel
    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var sensorStatusListAdapter: SensorStatusListAdapter
    private lateinit var sensorStatusRecyclerView: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewmodel = activity.let {
            val appContext = activity?.applicationContext as Application
            ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(appContext)
                .create(DashboardViewmodel::class.java)
        }

        viewmodel.sensorLiveData.value?.body().let {
            sensorStatusListAdapter = if(it != null) {
                SensorStatusListAdapter(it)
            } else {
                SensorStatusListAdapter(emptyList())
            }
            initRecyclerView()
        }

        viewmodel.sensorStatusLiveData.observe(viewLifecycleOwner, Observer {
            sensorStatusListAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        mapView = view.findViewById(R.id.map_view)
        initGoogleMap(savedInstanceState)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapExpandButton.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToFullScreenMapFragment()
            findNavController().navigate(action)
        }
    }

    private fun initGoogleMap(savedInstanceState: Bundle?) {
        if(savedInstanceState != null){
            mapView.onCreate(savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY))
        } else {
            mapView.onCreate(savedInstanceState)
        }
        mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setInfoWindowAdapter(createInfoWindow())

        viewmodel.sensorLiveData.observe(viewLifecycleOwner, Observer{
            if(it.isSuccessful){
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                    val averageLatLng = addMarkers(it.body()!!)
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(averageLatLng))
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    private fun initRecyclerView() {
        sensorStatusRecyclerView = RecyclerView(activity!!.applicationContext).apply{
            adapter = sensorStatusListAdapter
            layoutManager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        marker?.let{

            return true
        }
        return false
    }

    private fun addMarkers(sensors: List<SensorRecentResponse>): LatLng{

        var totalLat = 0.0
        var totalLng = 0.0

        for(sensor in sensors){
            val point = LatLng(sensor.latitude, sensor.longitude)
            totalLat += point.latitude
            totalLng += point.longitude

            val marker = MarkerOptions()
                .position(point)

            when(sensor.status){
                null -> marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pump_functioning))
                1 -> marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pump_no_data))
                2 -> marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pump_non_functioning))
            }

            mMap.addMarker(marker).tag = sensor
        }
        return LatLng(totalLat/sensors.size, totalLng/sensors.size)
    }

    private fun createInfoWindow(): GoogleMap.InfoWindowAdapter{
        return object: GoogleMap.InfoWindowAdapter{
            override fun getInfoWindow(marker: Marker?): View {
                val view = LayoutInflater.from(context!!).inflate(R.layout.marker_info_window_layout, null)
                view.view_details_layout.setOnClickListener {
                    //move to details layout screen
                    Toast.makeText(context!!, "HIIII", Toast.LENGTH_SHORT).show()
                }
                view.view_directions_layout.setOnClickListener {

                }
                view.view_logs_layout.setOnClickListener {

                }
                return view
            }

            override fun getInfoContents(p0: Marker?): View {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }

    override fun onStart() {
        super.onStart()
        map_view.onStart()
    }

    override fun onResume() {
        super.onResume()
        map_view.onResume()
    }

    override fun onStop() {
        super.onStop()
        map_view.onStop()
    }

    override fun onPause() {
        super.onPause()
        map_view.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_view.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map_view.onLowMemory()
    }
}