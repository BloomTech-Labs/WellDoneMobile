package com.versilistyson.welldone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.versilistyson.welldone.R
import com.versilistyson.welldone.data.remote.dto.PumpResponse
import com.versilistyson.welldone.data.remote.dto.SensorRecentResponse

class SensorStatusListAdapter(val sensors: List<SensorRecentResponse>): RecyclerView.Adapter<SensorStatusListAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun getItemCount(): Int {
        return sensors.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_pumpstatus, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}