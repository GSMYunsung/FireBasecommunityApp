package com.example.firebasecommunityapp.presentation.view.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.firebasecommunityapp.R
import com.example.firebasecommunityapp.databinding.FragmentFAQsBinding
import com.example.firebasecommunityapp.databinding.FragmentProfileBinding


class FAQsFragment : Fragment() {

    private lateinit var binding : FragmentFAQsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_f_a_qs, container, false)

        binding.backArrow.setOnClickListener {  this@FAQsFragment.findNavController().popBackStack() }
        return binding.root
    }

}