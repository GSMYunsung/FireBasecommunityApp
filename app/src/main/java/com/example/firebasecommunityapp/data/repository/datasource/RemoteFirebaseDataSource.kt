package com.example.firebasecommunityapp.data.repository.datasource

import com.example.firebasecommunityapp.data.model.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class RemoteFirebaseDataSource @Inject constructor(
    private val firebaseAuth : FirebaseAuth,
    private val firebaseRtdb : FirebaseDatabase,
    private val firebaseStorage : FirebaseStorage,
    private val fireStore : FirebaseFirestore
) {
    fun callUserInfo() = firebaseRtdb.reference.child("userInformation").child("callNumber")
    fun idUserInfo() = fireStore.collection("userNikcName").get()
    fun nickNameInfo() = fireStore.collection("userNikcName").get()
    fun postUserInformation(userinfo : UserData) = fireStore.collection("userNikcName").document(userinfo.nickName).set(userinfo)
}