package com.example.firebasecommunityapp.domain.usecase

import com.example.firebasecommunityapp.domain.repository.FirebaseRepository
import javax.inject.Inject

class CheckUserIdInfoUseCase @Inject constructor(
    private val firebaseRepository : FirebaseRepository
){
    fun excute() = firebaseRepository.idUserInfo()
}