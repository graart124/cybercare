package com.example.hatakon.screens.main.select_device.model

import com.example.hatakon.core.data.model.Device

data class SelectDeviceUiState(
    val deviceTypes: List<String> = emptyList(),
    val selectedDeviceType: String? = null,
    val deviceBrands: List<String> = emptyList(),
    val selectedDeviceBrand: String? = null,
    val devicesWithBrandAndType:List<Device> = emptyList(),
    val selectedDevice:Device? = null
)
