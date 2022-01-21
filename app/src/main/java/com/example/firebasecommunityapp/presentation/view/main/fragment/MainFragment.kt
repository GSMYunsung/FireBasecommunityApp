package com.example.firebasecommunityapp.presentation.view.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasecommunityapp.R
import com.example.firebasecommunityapp.databinding.FragmentMainBinding
import android.content.pm.PackageManager

import android.content.pm.PackageInfo
import android.util.Base64
import android.util.Log
import com.google.common.reflect.Reflection.getPackageName
import java.lang.Exception
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        return binding.root
    }

}
