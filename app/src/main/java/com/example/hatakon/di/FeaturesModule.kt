package com.example.hatakon.di

import android.app.Application
import androidx.room.Room
import com.example.hatakon.core.data.local.room.db.CyberCareDatabase
import com.example.hatakon.core.data.local.storage.UserDataLocalStorage
import com.example.hatakon.core.data.network.firebase.DeviceDatabase
import com.example.hatakon.features.device.repository.DeviceRepository
import com.example.hatakon.features.user.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeaturesModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        userDataLocalStorage: UserDataLocalStorage
    ): UserRepository {
        return UserRepository(userDataLocalStorage)
    }

    @Provides
    @Singleton
    fun provideDeviceRepository(deviceDataBase: DeviceDatabase, db:CyberCareDatabase): DeviceRepository {
        return DeviceRepository(deviceDataBase, db.deviceDao)
    }

    @Provides
    @Singleton
    fun provideCyberCareDatabase(app: Application): CyberCareDatabase {
        return Room.databaseBuilder(
            app,
            CyberCareDatabase::class.java,
            "cyber_care_app_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}