package com.example.firebasecommunityapp.presentation.di

import com.example.firebasecommunityapp.domain.repository.FirebaseRepository
import com.example.firebasecommunityapp.domain.usecase.CheckUserIdInfoUseCase
import com.example.firebasecommunityapp.domain.usecase.CheckUserInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModel {
    @Provides
    @Singleton
    fun provideCheckUserInfoUseCase(repository: FirebaseRepository) = CheckUserInfoUseCase(repository)

    @Provides
    @Singleton
    fun provideCheckUserIdInfoUseCase(repository: FirebaseRepository) = CheckUserIdInfoUseCase(repository)
}