package com.example.firebasecommunityapp.presentation.view.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.firebasecommunityapp.R
import com.example.firebasecommunityapp.databinding.FragmentMainBinding
import com.example.firebasecommunityapp.databinding.FragmentMapBinding

class MapFragment : Fragment() {

    private lateinit var binding : FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)

        binding.constraintLayout9.setOnClickListener { findNavController().navigate(R.id.action_mapFragment_to_mapFindFragment) }

        return binding.root
    }

}