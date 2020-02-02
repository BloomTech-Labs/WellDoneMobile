package com.versilistyson.welldone.presentation.ui.dashboard.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.versilistyson.welldone.R
import com.versilistyson.welldone.presentation.adapter.OperatorLogAdapter
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.presentation.viewmodel.SensorDialogViewModel
import kotlinx.android.synthetic.main.fragment_dialog_sensor_detail.*

class PumpDialogDetailFragment : DialogFragment(), OperatorLogAdapter.LogClickReceived {

    private lateinit var viewModel: SensorDialogViewModel
    private lateinit var sensor: Entity.Sensor
    private lateinit var logAdapter: OperatorLogAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_sensor_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sensor = arguments!!.getSerializable("sensor") as Entity.Sensor
        initViewModel()
        toolbar_pump_details.setNavigationOnClickListener{
            dismiss()
        }
        bindSensor()
//        viewModel.listOfLogs.add(
//            Entity.Log(
//                "13/05/2018", "13/06/2019",
//                ContextCompat.getDrawable(context!!, R.drawable.pump_non_functioning)!!, "HIMAN"
//            )
//        )
//        viewModel.listOfLogs.add(
//            Entity.Log(
//                "14/05/2020",
//                "15/06/2020",
//                ContextCompat.getDrawable(context!!, R.drawable.pump_no_data)!!,
//                "The pump was not working and it needed repairs"
//            )
//        )
        initRecyclerView()
    }

    override fun onLogClicked(log: Entity.LogDetails) {
        //start alert dialog for log that shows when a log on the list is clicked
        val logDialogFragment =
            LogDialogFragment()
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        val prev = activity!!.supportFragmentManager.findFragmentByTag("dialog2")
        if(prev != null){
            fragmentTransaction.remove(prev)
        }
        fragmentTransaction.addToBackStack(null)
        val bundle = Bundle()
        bundle.putSerializable("log", log)
        logDialogFragment.arguments = bundle
        logDialogFragment.show(fragmentTransaction, "dialog2")
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(SensorDialogViewModel::class.java)
    }

    private fun initRecyclerView() {
        logAdapter = OperatorLogAdapter(viewModel.listOfLogs, this)
        rv_logs.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = logAdapter
        }
    }

    private fun bindSensor() {
        tv_pump_id.text = resources.getString(R.string.pump_id_dialog, sensor.sensorId)
        tv_last_upload_date.text = sensor.lastUploadDate
        tv_well_depth.text = resources.getString(R.string.well_depth_dialog, sensor.wellDepth)
        tv_province.text = sensor.province
        tv_district.text = sensor.districtName
        tv_commune_value.text = sensor.commune
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }
}