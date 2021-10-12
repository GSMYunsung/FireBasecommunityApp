package com.example.firebasecommunityapp.presentation.view.signup

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebasecommunityapp.domain.usecase.CheckUserInfoUseCase
import com.google.firebase.database.DataSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewmodel @Inject constructor(
    private val checkUserInfoUseCase: CheckUserInfoUseCase
) : ViewModel() {

    private var phoneNumber : String? = null
    private var nickName : String? = null
    private var password : String? = null
    private var id : String? = null
    private var profilePicture : Uri? = null

    //회원가입중 다음으로 버튼을 활성화
    val checkGoNext: LiveData<Boolean> get() = _checkGoNext
    private val _checkGoNext = MutableLiveData<Boolean>()

    //코드가 보내졌는지 확인하는 변수
    val checkSendCodeIs : LiveData<Boolean> get() = _checkSendCodeIs
    private val _checkSendCodeIs = MutableLiveData<Boolean>()

    init {
        _checkGoNext.value = false
        _checkSendCodeIs.value = false
    }

    fun checkSendCode(){
        _checkSendCodeIs.value = true
    }

    fun goNextPage(){
        _checkGoNext.value= true
    }

    fun getPhoneNumber(phoneNumber : String){
        this.phoneNumber = phoneNumber
    }

    fun getIdAndPassword(id : String, password : String){
        this.id = id
        this.password = password
    }

    fun phoneNumberCheckNextCallUserInfo() = checkUserInfoUseCase.excute()

    fun phoneNumberDoubleCheck(snapshot: DataSnapshot){
       // if(snapshot.child("userInformation").child("callNumber").getValue())
    }

    fun getNicknameAndProfileP(nickName : String, profilePicture : Uri){
        this.nickName = nickName
        this.profilePicture = profilePicture
    }

}