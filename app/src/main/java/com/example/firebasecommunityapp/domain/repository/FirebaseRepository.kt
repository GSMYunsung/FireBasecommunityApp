package com.example.firebasecommunityapp.domain.repository

import com.google.firebase.database.DatabaseReference
import dagger.Provides

interface FirebaseRepository {
    //데이터베이스를 읽거나 쓸 수있다.
    fun callUserInfo() : DatabaseReference
    fun idUserInfo(id : String) : DatabaseReference
}