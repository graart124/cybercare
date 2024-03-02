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
}