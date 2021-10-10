package com.example.firebasecommunityapp.presentation.view.signup.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.firebasecommunityapp.R
import com.example.firebasecommunityapp.databinding.FragmentPhoneVerificationragmentBinding
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class PhoneVerificationragment : Fragment() {
    private lateinit var callbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var binding : FragmentPhoneVerificationragmentBinding
    private var verificationCode : String = ""
    private var isVerificationSussed = false
    var checkSendCodeIs = false
    private lateinit var resendingToken : PhoneAuthProvider.ForceResendingToken
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_phone_verificationragment, container, false)

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                //번호인증이 끝난 상태로 따로 인증번호를 입력할 필요없는 상태

            }

            override fun onVerificationFailed(p0: FirebaseException) {
                //번호인증이 실패한 상태 혹은, 이미 onCodeAutoRetrievalTimeOut 상태에 있는경우
                Toast.makeText(requireContext(), "잘못된 전화번호 또는 이미 인증코드가 전송되었습니다", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                //번호인증 시간이 다 되었을경우
                Toast.makeText(requireContext(), "인증코드가 유효하지 않거나 시간이 지났습니다.", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onCodeSent(verificationId: String, forceToken: PhoneAuthProvider.ForceResendingToken) {
                //번호는 확인 되었으나 인증코드를 입력해야 하는 상태
               verificationCode = verificationId
                resendingToken = forceToken
                checkSendCodeIs = true

            }
        }

        binding.nextFragment = this

        return binding.root
    }


    fun goToSetIdPassword(){
        if(isVerificationSussed){
            findNavController().navigate(R.id.action_phoneVerificationragment3_to_setIdPasswordFragment2)
        }
        else{
            Toast.makeText(activity,"우선 인증을 완료해주세요!",Toast.LENGTH_SHORT).show()
        }

    }

    fun checkPhoneAuthCode(view : View){
        if(checkSendCodeIs){
            if(TextUtils.isEmpty(binding.phoneCheckEditText.text)){
                Toast.makeText(activity,"인증번호를 입력해주세요!",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(),"인증중입니다. 잠시만 기다려주세요!",Toast.LENGTH_SHORT).show()
                var otp = binding.phoneCheckEditText.text.toString().trim()
                if(otp.isNotEmpty())
                {
                    Log.d("inside",otp)
                    codeCheckAndInner(otp)
                }
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    fun setNextButtonColor(){
        if(isVerificationSussed){binding.nextButton.setBackgroundColor(R.color.backcolor)}
    }

    private fun codeCheckAndInner(code: String) {
        val certification = PhoneAuthProvider.getCredential(verificationCode, code)
        checkCertificationQualifications(certification)
    }

    private fun checkCertificationQualifications(certification: PhoneAuthCredential){
        auth.signInWithCredential(certification)
            ?.addOnCompleteListener(requireActivity()){ task ->
                if(task.isSuccessful){
                    val user = task.result?.user
                    isVerificationSussed = true
                    setNextButtonColor()
                    Toast.makeText(activity,"인증이 완료되었습니다!",Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.w("errorLog",task.exception)
                        Toast.makeText(requireContext(),"인증번호가 잘못되었습니다.",Toast.LENGTH_SHORT)
                            .show()
                        binding.phoneCheckEditText.setText("")
                }
            }
    }

    fun clickPhoneAuthCode(view : View){
        if(TextUtils.isEmpty(binding.phoneEditText.text)){
            Toast.makeText(activity,"전화번호를 입력해주세요!",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(activity, "잠시만 기다려 주세요!", Toast.LENGTH_SHORT).show()
            val phoneNumber = binding.phoneEditText.text.substring(1)
            Log.d("PhoneNumber",phoneNumber)
            val options = auth.let {
                PhoneAuthOptions.newBuilder(it)
                        //$phoneNumber
                    .setPhoneNumber("+82 1018181111")
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(requireActivity())
                    .setCallbacks(callbacks)
                    .build()
            }
            if(options != null){PhoneAuthProvider.verifyPhoneNumber(options)}
        }
    }
}