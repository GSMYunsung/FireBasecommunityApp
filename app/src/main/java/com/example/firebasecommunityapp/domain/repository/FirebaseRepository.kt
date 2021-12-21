package com.example.firebasecommunityapp.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import dagger.Provides

interface FirebaseRepository {
    //데이터베이스를 읽거나 쓸 수있다.
    fun callUserInfo() : DatabaseReference
    fun idUserInfo() : Task<QuerySnapshot>
    fun nicknameInfo() :Task<QuerySnapshot>
}