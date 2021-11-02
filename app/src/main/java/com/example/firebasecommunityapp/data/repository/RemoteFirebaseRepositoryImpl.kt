package com.example.firebasecommunityapp.data.repository

import com.example.firebasecommunityapp.data.repository.datasource.RemoteFirebaseDataSource
import com.example.firebasecommunityapp.domain.repository.FirebaseRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject

class RemoteFirebaseRepositoryImpl @Inject constructor(
    private val fireBaseSource: RemoteFirebaseDataSource
) : FirebaseRepository {
    override fun callUserInfo(): DatabaseReference = fireBaseSource.callUserInfo()
    override fun idUserInfo(id : String): DatabaseReference = fireBaseSource.idUserInfo(id)
    override fun nicknameInfo(nickname: String): CollectionReference = fireBaseSource.nickNameInfo(nickname)
}