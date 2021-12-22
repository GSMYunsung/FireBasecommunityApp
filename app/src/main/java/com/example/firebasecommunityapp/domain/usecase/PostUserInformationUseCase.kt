package com.example.firebasecommunityapp.domain.usecase

import com.example.firebasecommunityapp.data.model.UserData
import com.example.firebasecommunityapp.domain.repository.FirebaseRepository
import javax.inject.Inject

class PostUserInformationUseCase @Inject constructor(
    private val firebaseRepository : FirebaseRepository
) {
    fun postUserInformation(userinfo : UserData) = firebaseRepository.postUserInformation(userinfo)
}