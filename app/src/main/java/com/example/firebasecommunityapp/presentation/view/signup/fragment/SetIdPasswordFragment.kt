package com.example.firebasecommunityapp.presentation.view.signup.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.firebasecommunityapp.R
import com.example.firebasecommunityapp.databinding.FragmentPhoneVerificationragmentBinding
import com.example.firebasecommunityapp.databinding.FragmentSetIdPasswordBinding
import com.example.firebasecommunityapp.presentation.view.signup.SignUpViewmodel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetIdPasswordFragment : Fragment() {
    private lateinit var binding: FragmentSetIdPasswordBinding

    private val signInViewModel by activityViewModels<SignUpViewmodel>()
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

    fun clickIdCheck(){
        if(TextUtils.isEmpty(binding.checkIdEditText.text)){
            Toast.makeText(activity,"ID를 입력해주세요!", Toast.LENGTH_SHORT).show()
        }
        else{
            signInViewModel.idCheckNextCallUserInfo(binding.checkIdEditText.text.toString()).addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {

                        if(snapshot.getValue() != null){
                            Toast.makeText(activity,"이미 존재하는 id 입니다. 다른 id를 입력해주세요!",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(activity,"사용가능한 id 입니다.",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("error",error.toString())
                    }

                }
            )
        }
    }

    fun getIdAndPassword(){

    }

    fun setNextButtonColor(){
        if(signInViewModel.checkGoNext.value == true){binding.nextButton.setBackgroundResource(R.color.backcolor)}
    }
}