package com.versilistyson.welldone.presentation.ui.dashboard.detail

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.versilistyson.welldone.R
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.presentation.util.makeInvisible
import com.versilistyson.welldone.presentation.util.makeVisible
import com.versilistyson.welldone.presentation.util.showCancelOrNotDialog
import kotlinx.android.synthetic.main.full_log_layout.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@FlowPreview
@InternalCoroutinesApi
class LogDialogFragment: DialogFragment() {

    private lateinit var currentLog: Entity.LogDetails
    private var currentLogPosition: Int? = null
    var listener: LogReceiver? = null

    private val PICK_PHOTO_REQUEST = 1

    interface LogReceiver {
        fun receiveLog(log: Entity.LogDetails, isAnUpdate: Boolean, position: Int = 0)
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.full_log_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            currentLog = it.getParcelable<Entity.LogDetails>("log")!!
            currentLogPosition = it.getInt("position")
            bindView()
        }

        img_pic_adder.setOnClickListener {
            //launches intent to open camera gallery
            val pickPhoto = Intent(Intent.ACTION_PICK)
            pickPhoto.type = "image/*"
            startActivityForResult(pickPhoto, PICK_PHOTO_REQUEST)
        }

        img_close_dialog.setOnClickListener {
            showCancelOrNotDialog()
        }

        btn_save.setOnClickListener {
            //saves or updates log
            listener?.let{
                val log = Entity.LogDetails(0, "05/02/2020", "05/02/2020",
                    (it as SensorDialogDetailFragment).sensor.sensorStatus, et_comment.text.toString(), null)

                it.receiveLog(log, ::currentLog.isInitialized)
            }
            dismiss()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            when (requestCode) {
                PICK_PHOTO_REQUEST -> {
                    data?.let {
                        val selectedImage = it.data
                        if (selectedImage != null) {
                            img_pic_adder.makeInvisible()
                            img_chosen.setImageURI(selectedImage)
                            img_chosen.makeVisible()
                        }
                    }
                }
            }
        }
    }

    private fun bindView() {
        tv_last_modified.text = "Last modified ${currentLog.lastModified}"
        et_comment.setText(currentLog.comment)
    }
}