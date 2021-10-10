package com.example.firebasecommunityapp.presentation.view.signup.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.firebasecommunityapp.R
import com.example.firebasecommunityapp.databinding.FragmentSetProfileBinding
import com.example.firebasecommunityapp.presentation.view.main.activity.MainActivity
import com.example.firebasecommunityapp.presentation.view.signup.activity.SignUpActivity
import com.google.firebase.auth.FirebaseAuth

class SetProfileFragment : Fragment() {
    private lateinit var binding : FragmentSetProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_profile, container, false)

        binding.startMain = this

        return binding.root

    }

    fun goToMain(){
        startActivity(Intent(activity, MainActivity::class.java))
    }
}