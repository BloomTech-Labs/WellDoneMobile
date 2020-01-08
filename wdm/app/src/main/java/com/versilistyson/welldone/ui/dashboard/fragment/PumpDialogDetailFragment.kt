package com.versilistyson.welldone.ui.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.versilistyson.welldone.R
import com.versilistyson.welldone.data.remote.dto.SensorRecentResponse
import kotlinx.android.synthetic.main.fragment_dialog_pump_detail.*

class PumpDialogDetailFragment : DialogFragment() {

    private lateinit var sensor: SensorRecentResponse
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_pump_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensor = arguments!!.getSerializable("sensor") as SensorRecentResponse

        bindSensor()
    }

    private fun bindSensor() {
        tv_pump_id.text = "Pump #${sensor.pump_index}"
        tv_last_upload_date.text = sensor.data_finished
        tv_well_depth.text = "${sensor.depth} feet"
        tv_province.text = "${sensor.province_name}"
        tv_district.text = "${sensor.district_name}"
        tv_commune_value.text = "${sensor.commune_name}"
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }
}

//        viewmodel = activity.let {
//            val appContext = activity?.applicationContext as Application
//            ViewModelProvider
//                .AndroidViewModelFactory
//                .getInstance(appContext)
//                .create(DashboardViewmodel::class.java)
//        }