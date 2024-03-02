package com.example.hatakon.features.user.di

import com.example.hatakon.core.data.local.storage.UserDataLocalStorage
import com.example.hatakon.features.user.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun provideLoginRepository(
        userDataLocalStorage: UserDataLocalStorage
    ): UserRepository {
        return UserRepository(userDataLocalStorage)
    }
}