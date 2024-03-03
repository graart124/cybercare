package com.example.hatakon.screens.main.devices

import androidx.lifecycle.ViewModel
import com.example.hatakon.features.device.repository.DeviceRepository
import com.example.hatakon.screens.main.devices.model.DeviceScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DeviceScreenViewModel @Inject constructor(
    val repository: DeviceRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(DeviceScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadDevices()
    }

    fun loadDevices() {
        _uiState.update { it.copy(loading = true) }
        repository.getAllDevices(
            onSuccess = { devices ->
                _uiState.update { it.copy(loading = false, devices = devices, errorText = null) }
            }, onFailure = {
                _uiState.update {
                    it.copy(
                        loading = false,
                        devices = emptyList(),
                        errorText = it.errorText
                    )
                }
            }
        )
    }
}