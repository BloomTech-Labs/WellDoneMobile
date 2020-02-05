package com.versilistyson.welldone.presentation.ui.dashboard.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.versilistyson.welldone.R
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.presentation.adapter.OperatorLogAdapter
import com.versilistyson.welldone.presentation.ui.dashboard.DashboardActivity
import com.versilistyson.welldone.presentation.viewmodel.SensorDialogViewModel
import kotlinx.android.synthetic.main.fragment_dialog_sensor_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class SensorDialogDetailFragment : DialogFragment(), OperatorLogAdapter.LogClickReceived, LogDialogFragment.LogReceiver {

    @Inject lateinit var vmFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SensorDialogViewModel
    lateinit var sensor: Entity.Sensor
    private lateinit var logAdapter: OperatorLogAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_sensor_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        arguments?.let{
            sensor = it.getParcelable("sensor")!!
            viewModel.retrieveLogs(sensor.sensorId)
        }

        toolbar_pump_details.setNavigationOnClickListener{
            dismiss()
        }
        bindSensor()

        viewModel.listOfLogs.observe(viewLifecycleOwner, Observer {
            if(!::logAdapter.isInitialized){
                logAdapter = OperatorLogAdapter(viewModel.listOfLogs.value!!, this)
                initRecyclerView()
            } else{
                logAdapter.notifyDataSetChanged()
            }
        })


        btn_add_log.setOnClickListener {
            moveToLogDialog()
        }
    }

    override fun receiveLog(log: Entity.LogDetails, isAnUpdate: Boolean, position: Int) {
        if(isAnUpdate){
            viewModel.updateLog(log, position)
        } else {
            viewModel.addLog(log)
        }
    }

    override fun onLogClicked(log: Entity.LogDetails, position: Int) {
        //start alert dialog for log that shows when a log on the list is clicked
        moveToLogDialog(log, position)
    }

    private fun moveToLogDialog(log: Entity.LogDetails? = null, position: Int? = null){
        val logDialogFragment = LogDialogFragment()
        logDialogFragment.listener = this
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        val prev = activity!!.supportFragmentManager.findFragmentByTag("dialog2")
        if(prev != null){
            fragmentTransaction.remove(prev)
        }
        fragmentTransaction.addToBackStack(null)
        log?.let {
            val bundle = Bundle()
            bundle.putParcelable("log", it)
            position?.let {
                bundle.putInt("position", position)
            }
            logDialogFragment.arguments = bundle
        }
        logDialogFragment.show(fragmentTransaction, "dialog2")
    }

    private fun initViewModel() {
        (activity as DashboardActivity).dashboardComponent.inject(this)
        viewModel = vmFactory.create(SensorDialogViewModel::class.java)
    }

    private fun initRecyclerView() {
        rv_logs.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = logAdapter
        }
    }

    private fun bindSensor() {
        tv_pump_id.text = resources.getString(R.string.sensor_id_dialog, sensor.sensorId)
        tv_last_upload_date.text = sensor.lastUploadDate
        tv_well_depth.text = String.format("%.1f", sensor.wellDepth) + " feet"
        tv_province.text = sensor.province
        tv_district.text = sensor.districtName
        tv_commune_value.text = sensor.commune
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }
}