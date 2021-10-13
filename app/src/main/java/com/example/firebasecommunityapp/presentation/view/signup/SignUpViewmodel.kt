package com.example.firebasecommunityapp.presentation.view.signup

import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebasecommunityapp.data.model.SingUp
import com.example.firebasecommunityapp.domain.usecase.CheckUserIdInfoUseCase
import com.example.firebasecommunityapp.domain.usecase.CheckUserInfoUseCase
import com.example.firebasecommunityapp.presentation.wiget.utils.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewmodel @Inject constructor(
    private var auth: FirebaseAuth,
    private val checkUserInfoUseCase: CheckUserInfoUseCase,
    private val checkUserIdInfoUseCase: CheckUserIdInfoUseCase
) : ViewModel() {

    private var phoneNumber : String? = null
    private var nickName : String? = null
    private var password : String? = null
    private var id : String? = null
    private var profilePicture : String? = null

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

    fun idCheckNextCallUserInfo(id : String) = checkUserIdInfoUseCase.excute(id)

    fun phoneNumberDoubleCheck(snapshot: DataSnapshot){
       if(snapshot.child(auth.uid.toString()).value != null){
            val userSignUpmModel = snapshot.child(auth.uid.toString()).getValue(SingUp::class.java)

            UserProfile.apply {
                phoneNumber = userSignUpmModel?.phoneNumber
                nickName = userSignUpmModel?.nickName
                password = userSignUpmModel?.password
                id = userSignUpmModel?.id
                profilePicture = userSignUpmModel?.profilePicture?.toUri()
            }

           //팝업창 띄우고 바로 메인화면 ㄱ 로직

       }
        else{
           goNextPage()
       }
    }

    fun getNicknameAndProfileP(nickName : String, profilePicture : String){
        this.nickName = nickName
        this.profilePicture = profilePicture
    }

}