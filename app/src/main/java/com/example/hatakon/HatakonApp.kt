package com.example.hatakon

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HatakonApp: Application(){
    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()
    }
}