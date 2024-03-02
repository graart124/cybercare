package com.example.hatakon.core.data.local.storage

import com.example.hatakon.core.data.model.UserData
import com.example.hatakon.core.data.model.toJsonString
import com.example.hatakon.core.data.model.toUserData
import javax.inject.Inject

private const val USER_DATA_PREFS = "user_data_prefs"

class UserDataLocalStorage @Inject constructor(
    private val encryptedStorageDataSource: EncryptedStorageDataSource
) {
    var user: UserData?
        get() = encryptedStorageDataSource.get(USER_DATA_PREFS)?.toUserData()
        set(value) = encryptedStorageDataSource.save(USER_DATA_PREFS, value?.toJsonString().orEmpty())
}