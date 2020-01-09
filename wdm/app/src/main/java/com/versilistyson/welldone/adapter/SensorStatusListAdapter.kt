package com.versilistyson.welldone.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.versilistyson.welldone.R
import com.versilistyson.welldone.data.remote.dto.SensorRecentResponse
import com.versilistyson.welldone.ui.dashboard.dialog.PumpDialogDetailFragment
import kotlinx.android.synthetic.main.card_pumpstatus.view.*

class SensorStatusListAdapter(val sensors: List<SensorRecentResponse>, val listener: DashboardToDetailsDialog? = null): RecyclerView.Adapter<SensorStatusListAdapter.ViewHolder>() {

    lateinit var context: Context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val pumpId = view.tv_PumpID as TextView
        val villageTitle = view.tv_villageTitle as TextView
        val communeTitle = view.tv_communeTitle as TextView
        val layout = view.layout_status as ConstraintLayout
        val moreDetailsBttn = view.expandAndClose_imageButton as ImageButton
        val llForDetails = view.ll_for_cvDetails as LinearLayout
        val dialogButton = view.dialog_button as Button

        fun dialogButtonOnClick(sensor: SensorRecentResponse, listener: DashboardToDetailsDialog){
            dialogButton.setOnClickListener {
                listener.moveToDialog(sensor)
            }
        }

        fun moreDetailsBttnOnClick() {
            moreDetailsBttn.setOnClickListener {
                when(llForDetails.visibility) {
                    View.VISIBLE -> {
                        llForDetails.visibility = View.GONE
                        moreDetailsBttn.setImageResource(R.drawable.ic_expand_more_black_24dp)

                    }

                    View.GONE -> {
                        llForDetails.visibility = View.VISIBLE
                        moreDetailsBttn.setImageResource(R.drawable.ic_expand_less_black_24dp)
                    }
                }

            }
        }
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
            }
            1    -> {
                holder.layout.setBackgroundColor(ContextCompat.getColor(context, R.color.no_data_status))
            }
            2    -> {
                holder.layout.setBackgroundColor(ContextCompat.getColor(context, R.color.working_status))
            }
        }
        holder.moreDetailsBttnOnClick()
        listener?.let { holder.dialogButtonOnClick(sensor, it) }
    }

    interface DashboardToDetailsDialog {
        fun moveToDialog(sensor: SensorRecentResponse)
    }
}