package com.example.firebasecommunityapp.presentation.view.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.firebasecommunityapp.R
import com.example.firebasecommunityapp.databinding.FragmentMainBinding
import com.example.firebasecommunityapp.databinding.FragmentProfileBinding
import com.pss.library.CountNumberEvent.Companion.countNumber

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)


//        countNumber(0,130,binding.textView11,3000)

        return binding.root
    }

}