package com.example.hatakon.screens.main.select_device

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hatakon.features.device.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectDeviceViewModel @Inject constructor(
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    init {
        deviceRepository.getAllDevices(
            onSuccess = { devices ->
                Log.d("MyTag", devices.size.toString())
            }, onFailure = { ex ->
                Log.d("MyTag", ex.message.toString())
            }
        )
    }
}