package com.example.hatakon.features.user.repository

import com.example.hatakon.core.data.local.storage.UserDataLocalStorage
import com.example.hatakon.core.data.model.UserData

class UserRepository(
    private val userDataLocalStorage: UserDataLocalStorage
) {
    fun saveUserData(
        userId: String?,
        email: String?,
        photoUrl: String?,
        name: String?
    ) {
        userDataLocalStorage.user = UserData(
            userId = userId,
            email = email,
            photoUrl = photoUrl,
            displayName = name
        )
    }

    fun getUserData(): UserData? {
        return userDataLocalStorage.user
    }
}