package com.versilistyson.welldone.ui.dashboard.fragment

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.versilistyson.welldone.R
import com.versilistyson.welldone.adapter.SensorStatusListAdapter
import com.versilistyson.welldone.data.remote.dto.SensorRecentResponse
import com.versilistyson.welldone.ui.dashboard.DashboardViewmodel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/*
    Dashboard map for WellDone operator where they can view the pumps on a map.
    Landscape and portrait mode. In landscape mode, map is bigger and pump status
    displays in a little box on the map when you click on a marker on the map. In
    portrait mode it displays in a little recycler view underneath, shows previous marker
    clicks as well.
 */
class DashboardFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var viewmodel: DashboardViewmodel
    private lateinit var mMap: GoogleMap
    private lateinit var sensorStatusListAdapter: SensorStatusListAdapter
    private lateinit var sensorStatusRecyclerView: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(map_view != null) {
            map_view.onCreate(null)
            map_view.onResume()
            map_view.getMapAsync(this)
        }

        viewmodel = activity.let {
            val appContext = activity?.applicationContext as Application
            ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(appContext)
                .create(DashboardViewmodel::class.java)
        }

        viewmodel.sensorLiveData.value?.body().let {
            if(it != null) {
                sensorStatusListAdapter = SensorStatusListAdapter(it)
            } else {
                sensorStatusListAdapter = SensorStatusListAdapter(emptyList())
            }
        }

        initRecyclerView()

        viewmodel = activity.let {
            val appContext = activity?.applicationContext as Application
            ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(appContext)
                .create(DashboardViewmodel::class.java)
        }

        viewmodel.sensorStatusLiveData.observe(viewLifecycleOwner, Observer {
            sensorStatusListAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    private fun initRecyclerView() {
        sensorStatusRecyclerView = RecyclerView(activity!!.applicationContext).apply{
            adapter = sensorStatusListAdapter
            layoutManager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        MapsInitializer.initialize(context)
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(15.0, 30.4)))
//        viewmodel.sensorLiveData.observe(viewLifecycleOwner, Observer{ result->
//            if(result.isSuccessful){
//                viewmodel.getAverageLatitudeLongitude(result.body()!!, mMap)
//            }
//        })
//        viewmodel.averageLatitudeLongitudeLiveData.observe(viewLifecycleOwner, Observer{
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(it))
//        })
    }

//    fun addMarkers(sensors: List<SensorRecentResponse>): LatLng{
//
//        var totalLat = 0.0
//        var totalLng = 0.0
//
//        for(sensor in sensors){
//            val point = LatLng(sensor.latitude, sensor.longitude)
//            totalLat += point.latitude
//            totalLng += point.longitude
//
//            val marker = MarkerOptions()
//                .position(point)
//
//            when(sensor.status){
//                null -> marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.no_data_marker))
//                1 -> marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.non_working_marker))
//                2 -> marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.working_marker))
//            }
//
//            mMap.addMarker(marker).tag = sensor
//        }
//        return LatLng(totalLat/sensors.size, totalLng/sensors.size)
//    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        marker?.let{
            viewmodel.addSensorStatus(marker.tag as SensorRecentResponse)
            return true
        }
        return false
    }
}
