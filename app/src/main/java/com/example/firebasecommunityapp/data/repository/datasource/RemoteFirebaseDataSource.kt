package com.example.firebasecommunityapp.data.repository.datasource

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
    fun idUserInfo(id : String) = firebaseRtdb.reference.child("allId").child(id)
}