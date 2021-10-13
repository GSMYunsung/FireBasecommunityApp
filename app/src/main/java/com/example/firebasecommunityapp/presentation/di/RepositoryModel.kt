package com.example.firebasecommunityapp.presentation.di

import com.example.firebasecommunityapp.data.repository.RemoteFirebaseRepositoryImpl
import com.example.firebasecommunityapp.data.repository.datasource.RemoteFirebaseDataSource
import com.example.firebasecommunityapp.domain.repository.FirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModel {
    @Provides
    @Singleton
    fun provideFirebaseRepository(
        remoteFirebaseDataSource: RemoteFirebaseDataSource
    ): FirebaseRepository {
        return RemoteFirebaseRepositoryImpl(
            remoteFirebaseDataSource
        )
    }
}