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
import com.example.firebasecommunityapp.databinding.FragmentSetIdPasswordBinding

class SetIdPasswordFragment : Fragment() {
    private lateinit var binding: FragmentSetIdPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_id_password, container, false)

        binding.nextFragment = this

        return binding.root
    }

    fun goToSetProfile(){
        findNavController().navigate(R.id.action_setIdPasswordFragment2_to_setProfileFragment2)
    }
}