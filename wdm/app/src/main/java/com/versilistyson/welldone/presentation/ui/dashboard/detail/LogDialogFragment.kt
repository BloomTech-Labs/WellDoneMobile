package com.versilistyson.welldone.presentation.ui.dashboard.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.versilistyson.welldone.R
import com.versilistyson.welldone.domain.framework.entity.Entity
import kotlinx.android.synthetic.main.full_log_layout.*

class LogDialogFragment: DialogFragment() {

    private lateinit var currentLog: Entity.LogDetails

    override fun getTheme(): Int {
        return R.style.AlertScreenDialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.full_log_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentLog = arguments!!.getSerializable("log") as Entity.LogDetails
        bindView()

        img_pic_adder.setOnClickListener {
            //launches intent to start camera
        }

        img_close_dialog.setOnClickListener {
            dismiss()
        }
    }

    private fun bindView() {
        tv_last_modified.text = "Last modified ${currentLog.lastModified}"
        et_comment.setText(currentLog.comment)
    }
}