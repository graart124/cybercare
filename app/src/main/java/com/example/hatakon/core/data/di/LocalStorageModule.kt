package com.example.hatakon.core.data.di

import android.content.Context
import com.example.hatakon.core.data.local.storage.EncryptedStorageDataSource
import com.example.hatakon.core.data.local.storage.UserDataLocalStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {

    @Provides
    @Singleton
    fun provideEncryptedStorageDataSource(@ApplicationContext context: Context) =
        EncryptedStorageDataSource(context)


//    @Provides
//    @Singleton
//    fun provideAnitaskAppDatabase(app: Application): AnitaskAppDatabase {
//        return Room.databaseBuilder(
//            app,
//            AnitaskAppDatabase::class.java,
//            "indy_app_db"
//        )
//            .fallbackToDestructiveMigration()
//            .build()
//    }

    @Provides
    @Singleton
    fun provideUserDataLocalStorage(encryptedStorageDataSource: EncryptedStorageDataSource)
            : UserDataLocalStorage {
        return UserDataLocalStorage(encryptedStorageDataSource)
    }

}