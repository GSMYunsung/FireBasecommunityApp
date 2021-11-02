package com.example.firebasecommunityapp.domain.usecase

import com.example.firebasecommunityapp.domain.repository.FirebaseRepository
import javax.inject.Inject

class CheckUserNicknameUseCase @Inject constructor(
    private val firebaseRepository : FirebaseRepository
) {
    fun checkNickname(nickname : String) = firebaseRepository.nicknameInfo(nickname)
}