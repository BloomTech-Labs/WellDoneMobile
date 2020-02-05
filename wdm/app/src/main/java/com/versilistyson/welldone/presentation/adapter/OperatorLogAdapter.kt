package com.versilistyson.welldone.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.versilistyson.welldone.R
import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.android.synthetic.main.log_entry_layout.view.*

class OperatorLogAdapter(private val logs: MutableList<Entity.LogDetails>, val listener: LogClickReceived? = null): RecyclerView.Adapter<OperatorLogAdapter.LogViewHolder>() {

    interface LogClickReceived {
        fun onLogClicked(log: Entity.LogDetails, position: Int)
    }

    inner class LogViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun viewClickListener(log: Entity.LogDetails, position: Int){
            view.setOnClickListener {
                listener?.onLogClicked(log, position)
            }
        }

        val tvDateCreated = view.tv_date_created
        val tvLastEdited = view.tv_last_modified
        val imgStatus = view.img_status
        val tvComment = view.tv_comments
    }

    override fun getItemCount(): Int = logs.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        return LogViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.log_entry_layout, parent, false))
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        val log = logs[position]
        holder.viewClickListener(log, position)
        holder.tvDateCreated.text = log.dateFiled.substring(0, 10)
        holder.tvLastEdited.text = "                   "
        log.lastModified?.let {
            holder.tvLastEdited.text = it.substring(0, 10)
        }
        holder.imgStatus.setImageDrawable(
            when(log.status){
                null -> ContextCompat.getDrawable(holder.view.context, R.drawable.pump_functioning)
                1 -> ContextCompat.getDrawable(holder.view.context, R.drawable.pump_no_data)
                else -> ContextCompat.getDrawable(holder.view.context, R.drawable.pump_non_functioning)
            }
        )
        holder.tvComment.text = log.comment
    }
}