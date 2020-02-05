package com.versilistyson.welldone.presentation.ui.dashboard.detail

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.versilistyson.welldone.R
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.presentation.ui.dashboard.DashboardActivity
import com.versilistyson.welldone.presentation.util.beGone
import com.versilistyson.welldone.presentation.util.makeVisible
import com.versilistyson.welldone.presentation.util.showCancelOrNotDialog
import com.versilistyson.welldone.presentation.viewmodel.LogDialogViewModel
import kotlinx.android.synthetic.main.full_log_layout.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@InternalCoroutinesApi
class LogDialogFragment: DialogFragment() {

    private lateinit var currentLog: Entity.LogDetails
    private var currentLogPosition: Int? = null
    @Inject lateinit var vmFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LogDialogViewModel
    private var imageUri: Uri? = null
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

        initViewModel()

        arguments?.let{
            currentLog = it.getParcelable("log")!!
            currentLogPosition = it.getInt("position")
            viewModel.getLogImagesForLogId(currentLog.logId)
            bindView()
        }

        img_pic_adder.setOnClickListener {
            //launches intent to open camera gallery
            val pickPhoto = Intent(Intent.ACTION_PICK)
            pickPhoto.type = "image/*"
            startActivityForResult(pickPhoto, PICK_PHOTO_REQUEST)
        }

        viewModel.imageLink.observe(viewLifecycleOwner, Observer {
            Glide.with(this).load(it.imageLink).into(img_chosen)
            img_chosen.makeVisible()
            img_pic_adder.beGone()
            img_delete_img.makeVisible()
            input_layout.makeVisible()
            et_caption_text.setText(it.caption)
        })

        img_close_dialog.setOnClickListener {
            showCancelOrNotDialog()
        }

        btn_save.setOnClickListener {
            //saves or updates log
            listener?.let{
                var uriImage: Uri? = null
                var logImage: Entity.LogImage? = null
                if(img_chosen.visibility == View.VISIBLE){
                    uriImage = imageUri
                    logImage = Entity.LogImage(
                        0,
                        et_caption_text.text.toString(),
                        "",
                        uriImage
                    )
                }
                //posting this image to this specific log id
                val log = Entity.LogDetails(0, "05/02/2020", "05/02/2020",
                    (it as SensorDialogDetailFragment).sensor.sensorStatus, et_comment.text.toString(), logImage)

                it.receiveLog(log, ::currentLog.isInitialized)
            }
            dismiss()
        }

        img_delete_img.setOnClickListener {
            img_chosen.beGone()
            img_delete_img.beGone()
            input_layout.beGone()
            img_pic_adder.makeVisible()
        }
    }

    private fun initViewModel() {
        (activity as DashboardActivity).dashboardComponent.inject(this)
        viewModel = vmFactory.create(LogDialogViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            when (requestCode) {
                PICK_PHOTO_REQUEST -> {
                    data?.let {
                        val selectedImage = it.data
                        if (selectedImage != null) {
                            imageUri = selectedImage
                            img_pic_adder.beGone()
                            img_chosen.setImageURI(selectedImage)
                            img_chosen.makeVisible()
                            img_delete_img.makeVisible()
                            input_layout.makeVisible()
                        }
                    }
                }
            }
        }
    }

    private fun bindView() {
        if(currentLog.lastModified != null) {
            tv_last_modified.text = "Last modified ${currentLog.lastModified!!.substring(0, 10)}"
        }
        et_comment.setText(currentLog.comment)
        if(currentLog.imageDetails?.imageUrl != null){
            img_chosen.setImageURI(currentLog.imageDetails?.imageUrl)
            img_chosen.makeVisible()
            img_pic_adder.beGone()
        }
    }
}