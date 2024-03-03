package com.example.hatakon.screens.main.profile.model

import com.example.hatakon.core.data.model.Device
import com.example.hatakon.core.data.model.SecureType
import com.example.hatakon.core.data.model.UserData

data class ProfileUiState(
    val user: UserData?=null,
    val recentDevicesChecks:List<Device> = emptyList(),
    val selectedSecureType:SecureType? = null
)
