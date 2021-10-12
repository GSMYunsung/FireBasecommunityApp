package com.example.firebasecommunityapp.data.repository

import com.example.firebasecommunityapp.data.repository.datasource.RemoteFirebaseDataSource
import com.example.firebasecommunityapp.domain.repository.FirebaseRepository
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class RemoteFirebaseDataSourceImpl @Inject constructor(
    private val fireBaseSource: RemoteFirebaseDataSource
) : FirebaseRepository {
    override fun callUserInfo(): DatabaseReference = fireBaseSource.callUserInfo()

}