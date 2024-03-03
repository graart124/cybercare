package com.example.hatakon.screens.main.devices.model

import com.example.hatakon.core.data.model.Device

data class DeviceScreenUiState(
    val devices:List<Device> = emptyList(),
    val loading:Boolean = false,
    val errorText:String? = null
)
