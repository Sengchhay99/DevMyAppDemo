package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDashBoardBinding

class DashboardFragment : Fragment(R.layout.fragment_dash_board) {

    private val binding: FragmentDashBoardBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}