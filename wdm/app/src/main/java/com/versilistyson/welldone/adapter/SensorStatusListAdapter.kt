package com.versilistyson.welldone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.versilistyson.welldone.R
import com.versilistyson.welldone.data.remote.dto.SensorRecentResponse
import kotlinx.android.synthetic.main.card_pumpstatus.view.*

class SensorStatusListAdapter(val sensors: List<SensorRecentResponse>): RecyclerView.Adapter<SensorStatusListAdapter.ViewHolder>() {

    lateinit var context: Context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val pumpId = view.tv_PumpID as TextView
        val villageTitle = view.tv_villageTitle as TextView
        val communeTitle = view.tv_communeTitle as TextView
        val imgIcon = view.img_statusIcon as ImageView
        val layout = view.layout_status as ConstraintLayout
        val moreDetails = view.expand_image_button as ImageButton
    }

    override fun getItemCount(): Int {
        return sensors.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_pumpstatus, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sensor = sensors[position]
        holder.pumpId.text = "${sensor.pump_index}"
        holder.villageTitle.text = sensor.village_name
        holder.communeTitle.text = sensor.commune_name

        when(sensor.status){
            null -> {
                holder.layout.setBackgroundColor(ContextCompat.getColor(context, R.color.non_working_status))
                holder.imgIcon.setImageDrawable(getDrawable(context, R.drawable.red_x))
            }
            1    -> {
                holder.layout.setBackgroundColor(ContextCompat.getColor(context, R.color.no_data_status))
            }
            2    -> {
                holder.layout.setBackgroundColor(ContextCompat.getColor(context, R.color.working_status))
                holder.imgIcon.setImageDrawable(getDrawable(context, R.drawable.green_check))
            }
        }

        holder.moreDetails.setOnClickListener {

        }
    }
}