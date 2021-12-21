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
import java.util.regex.Pattern

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

        signInViewModel.checkUserPasswordIs.observe(
            viewLifecycleOwner, {
                signInViewModel.checkUserIdIs.observe(
                    viewLifecycleOwner,{
                        binding.nextButton.setBackgroundResource(R.color.backcolor)
                    }
                )
            }
        )

        binding.nextFragment = this

        return binding.root
    }

    fun clickIdCheck(){
        if(TextUtils.isEmpty(binding.checkIdEditText.text)){
            Toast.makeText(activity,"ID를 입력해주세요!", Toast.LENGTH_SHORT).show()
        }
        else{
            signInViewModel.idCheckNextCallUserInfo().addOnSuccessListener {

                for (i in 0 until it.size()){

                    if(i == it.size()-1)
                    {

                        if (it.documents[i].data!!.get("uid") == binding.checkIdEditText.text.toString()){
                            Toast.makeText(activity,"이미 존재하는 id 입니다. 다른 id를 입력해주세요!",Toast.LENGTH_SHORT).show()
                            signInViewModel.checkIdPasswordChange()
                        }
                        else if(!Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{6,10}$",binding.checkIdEditText.text)){
                            Toast.makeText(activity,"형식에 맞지않는 id 입니다. 다시 입력해주세요!",Toast.LENGTH_SHORT).show()
                            signInViewModel.checkIdPasswordChange()
                        }
                        else{
                            Toast.makeText(activity,"사용가능한 id 입니다.",Toast.LENGTH_SHORT).show()
                            signInViewModel.checkIdIs()
                        }

                    }
                }

            }
        }
    }

    fun clickPasswordCheck(){


        val password = binding.checkPasswordEditText.text

        if(TextUtils.isEmpty(binding.checkPasswordEditText.text.toString()) && TextUtils.isEmpty(binding.checkPasswordAgainEditText.text.toString())){
            Toast.makeText(activity,"비밀번호칸과 비밀번호 확인칸 모두 입력해주세요!",Toast.LENGTH_SHORT).show()
            signInViewModel.checkIdPasswordChange()
        }
        else if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{6,12}.\$",password)){
            Toast.makeText(activity,"비밀번호 형식에 맞지 않습니다. 다시한번 입력해주세요!",Toast.LENGTH_SHORT).show()
            signInViewModel.checkIdPasswordChange()
        }
        else if(binding.checkPasswordEditText.text.toString() != binding.checkPasswordAgainEditText.text.toString()){
            Toast.makeText(activity,"비밀번호 확인칸과 비밀번호칸이 일치하지 않습니다. 다시한번 시도해주세요!",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(activity,"사용가능한 비밀번호입니다!",Toast.LENGTH_SHORT).show()
            signInViewModel.checkPasswordIs()
        }
    }

    fun getIdAndPassword(){

        if(signInViewModel.checkUserPasswordIs.value == true && signInViewModel.checkUserIdIs.value == true){
            signInViewModel.getIdAndPassword(binding.checkIdEditText.text.toString(),binding.checkPasswordEditText.text.toString())
            findNavController().navigate(R.id.action_setIdPasswordFragment2_to_setProfileFragment2)
        }
        else{
            Toast.makeText(activity,"우선 인증을 완료해주세요!",Toast.LENGTH_SHORT).show()
        }
    }
}