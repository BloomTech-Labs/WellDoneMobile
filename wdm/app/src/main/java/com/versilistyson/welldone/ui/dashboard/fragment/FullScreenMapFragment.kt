package com.versilistyson.welldone.ui.dashboard.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.versilistyson.welldone.R

class FullScreenMapFragment : Fragment() {

    companion object {
        fun newInstance() = FullScreenMapFragment()
    }

    private lateinit var viewModel: FullScreenMapViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.full_screen_map_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FullScreenMapViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
