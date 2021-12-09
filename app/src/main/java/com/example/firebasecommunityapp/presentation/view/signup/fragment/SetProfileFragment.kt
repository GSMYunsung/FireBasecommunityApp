package com.example.firebasecommunityapp.presentation.view.signup.fragment

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebasecommunityapp.R
import com.example.firebasecommunityapp.databinding.FragmentSetProfileBinding
import com.example.firebasecommunityapp.presentation.view.main.activity.MainActivity
import com.example.firebasecommunityapp.presentation.view.signup.SignUpViewmodel
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.firestore.ktx.getField
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Flow
import java.util.regex.Pattern


@Suppress("DEPRECATION")
@AndroidEntryPoint
class SetProfileFragment : Fragment() {
    private var proFileUri: Uri? = null
    private val signInViewModel by activityViewModels<SignUpViewmodel>()
    private lateinit var binding: FragmentSetProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_profile, container, false)

        binding.startMain = this


        signInViewModel.checkUserPictureIs.observe(
            viewLifecycleOwner, {
                signInViewModel.checkUserNicknameIs.observe(
                    viewLifecycleOwner, {
                        binding.button.setBackgroundResource(R.color.backcolor)
                    }
                )
            }
        )

        return binding.root

    }

    fun getUserProfileImage() {
        val intent = Intent()

        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        ImagePicker.with(this)
            .compress(1024)
            .start()
    }

    //해당 함수에서 응답을 처리해준다.
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            2404 -> {
                proFileUri = data?.data!!

                binding.profileImageView.setImageURI(proFileUri)

                signInViewModel.checkProfileIs()
            }
            ImagePicker.RESULT_ERROR -> {
                proFileUri = null
                Toast.makeText(
                    requireContext(),
                    "비정상적인 접근입니다. 다시한번 이미지를 선택해주세요",
                    Toast.LENGTH_SHORT
                )
                    .show()

                signInViewModel.checkNickNameProfileChange()
            }
            else -> {
                Toast.makeText(requireContext(), "작업 취소됨. 다시한번 이미지를 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun nickNameCheck() {

        if (TextUtils.isEmpty(binding.nicknameEditText.text)) {
            Toast.makeText(activity, "닉네임을 입력해주세요!", Toast.LENGTH_SHORT).show()
        } else {
            signInViewModel.nicknameCheckInfo().addOnSuccessListener {
                for (i in 0..it.size()-1) {
                    if (it.documents.get(i).id == binding.nicknameEditText.text.toString()) {
                        Toast.makeText(
                            activity,
                            "이미 존재하는 닉네임 입니다. 다른 닉네임을 입력해주세요!",
                            Toast.LENGTH_SHORT
                        ).show()
                        signInViewModel.checkNickNameProfileChange()
                    }
                    else if (!Pattern.matches("^[가-힣ㄱ-ㅎa-zA-Z0-9._-]{2,}\$", binding.nicknameEditText.text))
                    {
                        Toast.makeText(
                            activity,
                            "형식에 맞지않는 id 입니다. 형식에 맞게 다시 입력해주세요",
                            Toast.LENGTH_SHORT
                        ).show()
                        signInViewModel.checkNickNameProfileChange()
                    }
                    else {
                        Toast.makeText(activity, "사용가능한 닉네임 입니다.", Toast.LENGTH_SHORT).show()
                        signInViewModel.checkNickNameIs()
                    }
                }
            }
        }
    }

        fun getNickname() {
            if (signInViewModel.checkUserNicknameIs.value == true && signInViewModel.checkUserPictureIs.value == true) {
                signInViewModel.getNicknameAndProfileP(
                    binding.nicknameEditText.text.toString(),
                    binding.profileImageView.toString()
                )
            } else {
                Toast.makeText(activity, "우선모든 빈칸을 완료해주세요!", Toast.LENGTH_SHORT).show()
            }
        }

    }