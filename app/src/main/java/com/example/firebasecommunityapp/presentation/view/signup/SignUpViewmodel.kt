package com.example.firebasecommunityapp.presentation.view.signup

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebasecommunityapp.data.model.SingUp
import com.example.firebasecommunityapp.domain.repository.FirebaseRepository
import com.example.firebasecommunityapp.domain.usecase.CheckUserIdInfoUseCase
import com.example.firebasecommunityapp.domain.usecase.CheckUserInfoUseCase
import com.example.firebasecommunityapp.domain.usecase.CheckUserNicknameUseCase
import com.example.firebasecommunityapp.presentation.wiget.utils.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewmodel @Inject constructor(
    private var auth: FirebaseAuth,
    private val checkUserInfoUseCase: CheckUserInfoUseCase,
    private val checkUserIdInfoUseCase: CheckUserIdInfoUseCase,
    private val checkUserNicknameUseCase: CheckUserNicknameUseCase
) : ViewModel() {

    private var phoneNumber : String? = null
    private var nickName : String? = null
    private var password : String? = null
    private var id : String? = null
    private var profilePicture : String? = null

    //회원가입중 다음으로 버튼을 활성화
    val checkGoNext: LiveData<Boolean> get() = _checkGoNext
    private val _checkGoNext = MutableLiveData<Boolean>()

    //현재 사용자의 닉네임이 정상적인 닉네임?

    val checkUserNicknameIs: LiveData<Boolean> get() = _checkUserNicknameIs
    private val _checkUserNicknameIs = MutableLiveData<Boolean>()

    //현재 사용자의 사진이 정상적인 사진?

    val checkUserPictureIs: LiveData<Boolean> get() = _checkUserPictureIs
    private val _checkUserPictureIs = MutableLiveData<Boolean>()

    //현재 사용자가 설정한 비밀번호가 정상적인 비밀번호??

    val checkUserPasswordIs: LiveData<Boolean> get() = _checkUserPasswordIs
    private val _checkUserPasswordIs = MutableLiveData<Boolean>()

    //현재 사용자가 설정한 비밀번호가 정상적인 아이디??

    val checkUserIdIs: LiveData<Boolean> get() = _checkUserIdIs
    private val _checkUserIdIs = MutableLiveData<Boolean>()


    //코드가 보내졌는지 확인하는 변수
    val checkSendCodeIs : LiveData<Boolean> get() = _checkSendCodeIs
    private val _checkSendCodeIs = MutableLiveData<Boolean>()

    init {
        _checkGoNext.value = false
        _checkSendCodeIs.value = false
    }

    fun checkPasswordIs(){
        _checkUserPasswordIs.value = true
    }

    fun checkNickNameIs(){
        _checkUserNicknameIs.value = true
    }

    fun checkNickNameProfileChange(){
        _checkUserNicknameIs.value = false
        _checkUserPictureIs.value = false
    }

    fun checkProfileIs(){
        _checkUserPictureIs.value = true
    }

    fun checkIdPasswordChange(){
        _checkUserIdIs.value = false
        _checkUserPasswordIs.value = false
    }

    fun checkIdIs(){
        _checkUserIdIs.value = true
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

    fun nicknameCheckInfo() = checkUserNicknameUseCase.checkNickname()

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