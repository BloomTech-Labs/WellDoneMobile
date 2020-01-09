package com.versilistyson.welldone.ui.dashboard.dialog.log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.versilistyson.welldone.R
import com.versilistyson.welldone.data.local.model.OperatorLog
import kotlinx.android.synthetic.main.full_log_layout.*

class LogDialogFragment: DialogFragment() {

    private lateinit var currentLog: OperatorLog

    override fun getTheme(): Int {
        return R.style.AlertScreenDialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.full_log_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentLog = arguments!!.getSerializable("log") as OperatorLog
        bindView()
    }

    private fun bindView() {
        tv_last_modified.text = "Last modified ${currentLog.last_modified}"
        et_comment.setText(currentLog.comment)
    }
}