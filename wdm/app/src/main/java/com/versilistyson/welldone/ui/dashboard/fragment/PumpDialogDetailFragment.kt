package com.versilistyson.welldone.ui.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.versilistyson.welldone.R

class PumpDialogDetailFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        viewmodel = activity.let {
//            val appContext = activity?.applicationContext as Application
//            ViewModelProvider
//                .AndroidViewModelFactory
//                .getInstance(appContext)
//                .create(DashboardViewmodel::class.java)
//        }
        return inflater.inflate(R.layout.fragment_dialog_pump_detail, container, false)
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }
}
