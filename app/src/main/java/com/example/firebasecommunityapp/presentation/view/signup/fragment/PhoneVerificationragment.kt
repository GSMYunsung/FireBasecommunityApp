package com.example.firebasecommunityapp.presentation.view.signup.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.firebasecommunityapp.R
import com.example.firebasecommunityapp.databinding.FragmentPhoneVerificationragmentBinding

class PhoneVerificationragment : Fragment() {
    private lateinit var binding : FragmentPhoneVerificationragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_phone_verificationragment, container, false)

        binding.nextFragment = this

        return binding.root
    }

    fun goToSetIdPassword(){
        findNavController().navigate(R.id.action_phoneVerificationragment3_to_setIdPasswordFragment2)
    }
}