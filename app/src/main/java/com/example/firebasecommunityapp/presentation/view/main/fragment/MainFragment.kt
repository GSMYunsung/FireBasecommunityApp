package com.example.firebasecommunityapp.presentation.view.main.fragment

import android.gesture.GestureOverlayView.ORIENTATION_HORIZONTAL
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.firebasecommunityapp.R
import com.example.firebasecommunityapp.databinding.ActivityMainBinding
import com.example.firebasecommunityapp.databinding.FragmentMainBinding
import com.example.firebasecommunityapp.presentation.view.main.adapter.MainCatagoryAdapter


class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.mainCatagoryViewpager.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.mainCatagoryViewpager.setHasFixedSize(true)
        binding.mainCatagoryViewpager.adapter = MainCatagoryAdapter(setCatagory())

        return binding.root
    }

    private fun setCatagory(): ArrayList<String> {
        return arrayListOf("All", "Food","Join","Myself")
    }
}