package com.example.hatakon.features.device.repository

import com.example.hatakon.core.data.model.Device
import com.example.hatakon.core.data.network.firebase.DeviceDatabase

class DeviceRepository(
    private val database:DeviceDatabase
) {
    fun getAllDevices(
        onSuccess: (List<Device>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        database.getAllDevices(
            onSuccess,
            onFailure
        )
    }
    fun getAllDeviceTypes(
        onSuccess: (List<String>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
       database.getAllDeviceTypes(onSuccess,onFailure)
    }

    fun getDeviceBrandsByType(
        deviceType: String,
        onSuccess: (List<String>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        database.getDeviceBrandsByType(deviceType, onSuccess, onFailure)
    }

    fun getDevicesByTypeAndBrand(
        deviceType: String,
        deviceBrand: String,
        onSuccess: (List<Device>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        database.getDevicesByTypeAndBrand(deviceType, deviceBrand, onSuccess, onFailure)
    }


}