package com.versilistyson.welldone.ui.dashboard.fragment

/*
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.versilistyson.welldone.R
import com.versilistyson.welldone.databinding.FragmentPumpDetailBinding
import com.versilistyson.welldone.ui.dashboard.DashboardViewmodel

*//**
 * A simple [Fragment] subclass.
 *//*
class PumpDetailFragment : Fragment() {

    private lateinit var viewmodel: DashboardViewmodel
    private lateinit var binding: FragmentPumpDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewmodel = activity.let {
            val appContext = activity?.applicationContext as Application
            ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(appContext)
                .create(DashboardViewmodel::class.java)
        }

        binding = FragmentPumpDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@PumpDetailFragment
            pump = viewmodel.selectedMarkerSensor!!
        }
        return binding.root
    }
}*/
