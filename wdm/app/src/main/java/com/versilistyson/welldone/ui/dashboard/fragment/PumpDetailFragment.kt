package com.versilistyson.welldone.ui.dashboard.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.versilistyson.welldone.R

/**
 * A simple [Fragment] subclass.
 */
class PumpDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pump_detail, container, false)
    }


}
