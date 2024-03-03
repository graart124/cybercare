package com.example.hatakon.screens.main.device_info_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hatakon.core.data.model.Device
import com.example.hatakon.features.device.repository.DeviceRepository
import com.example.hatakon.features.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceInfoScreenViewModel @Inject constructor(
    private val repository: DeviceRepository
) : ViewModel() {

    fun insertRecentDevices(device: Device){
        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
            }
        ) {
            try {
                repository.insertDevice(device)
            }
            catch (e: Exception){
                Log.d("errorTag",e.message.toString())
            }
        }

    }
}