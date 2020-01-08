package com.versilistyson.welldone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.versilistyson.welldone.R
import com.versilistyson.welldone.data.remote.dto.OperatorLog
import kotlinx.android.synthetic.main.log_entry_layout.view.*

class OperatorLogAdapter(val logs: MutableList<OperatorLog>): RecyclerView.Adapter<OperatorLogAdapter.LogViewHolder>() {

    class LogViewHolder(view: View): RecyclerView.ViewHolder(view){
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
        holder.tvDateCreated.text = log.date_filed
        holder.tvLastEdited.text = log.last_modified
        holder.imgStatus.setImageDrawable(log.status)
        holder.tvComment.text = log.comment
    }
}