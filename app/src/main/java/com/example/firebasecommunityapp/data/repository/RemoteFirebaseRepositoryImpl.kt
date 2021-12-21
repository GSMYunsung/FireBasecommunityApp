package com.example.firebasecommunityapp.data.repository

import com.example.firebasecommunityapp.data.repository.datasource.RemoteFirebaseDataSource
import com.example.firebasecommunityapp.domain.repository.FirebaseRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class RemoteFirebaseRepositoryImpl @Inject constructor(
    private val fireBaseSource: RemoteFirebaseDataSource
) : FirebaseRepository {
    override fun callUserInfo(): DatabaseReference = fireBaseSource.callUserInfo()
    override fun idUserInfo(): Task<QuerySnapshot> = fireBaseSource.idUserInfo()
    override fun nicknameInfo(): Task<QuerySnapshot> = fireBaseSource.nickNameInfo()
}