package com.example.hatakon.screens.main.select_device

import androidx.lifecycle.ViewModel
import com.example.hatakon.core.data.model.Device
import com.example.hatakon.features.device.repository.DeviceRepository
import com.example.hatakon.screens.main.select_device.model.SelectDeviceUiState
import com.example.hatakon.ui.util.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SelectDeviceViewModel @Inject constructor(
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SelectDeviceUiState())
    val uiState = _uiState.asStateFlow()

    private val _actionResult = MutableStateFlow(Action())
    val actionResult = _actionResult.asStateFlow()


    init {
        _uiState.update { it.copy(loading = true) }
        deviceRepository.getAllDeviceTypes(
            onSuccess = { deviceTypes ->
                _uiState.update { it.copy(deviceTypes = deviceTypes, loading = false) }
            }, onFailure = {
                _uiState.update { it.copy(loading = false) }
                _actionResult.update {
                    it.copy(
                        info = "Error while load device types, try again",
                        success = false
                    )
                }
            }
        )
    }

    fun selectDeviceType(deviceType: String) {
        _uiState.update {
            SelectDeviceUiState(
                deviceTypes = it.deviceTypes,
                selectedDeviceType = deviceType,
                loading = true
            )
        }
        deviceRepository.getDeviceBrandsByType(
            deviceType = deviceType,
            onSuccess = { deviceBrands ->
                _uiState.update { it.copy(deviceBrands = deviceBrands, loading = false) }
            }, onFailure = {
                _uiState.update { it.copy(loading = false) }
                _actionResult.update {
                    it.copy(
                        info = "Error while load device brands, pls try again",
                        success = false
                    )
                }
            }
        )
    }

    fun selectBrand(deviceBrand: String) {
        _uiState.update {
            it.copy(
                selectedDeviceBrand = deviceBrand,
                selectedDevice = null,
                devicesWithBrandAndType = emptyList(),
                loading = true
            )
        }
        deviceRepository.getDevicesByTypeAndBrand(
            deviceType = _uiState.value.selectedDeviceType!!,
            deviceBrand = deviceBrand,
            onSuccess = { devices ->
                _uiState.update { it.copy(devicesWithBrandAndType = devices, loading = false) }
            }, onFailure = {
                _uiState.update { it.copy(loading = false) }
                _actionResult.update {
                    it.copy(
                        info = "Error while load devices, pls try again",
                        success = false
                    )
                }
            }
        )
    }

    fun selectDevice(device: Device) {
        _uiState.update { it.copy(selectedDevice = device) }
    }

    fun clearData() {
        _uiState.update { SelectDeviceUiState(deviceTypes = it.deviceTypes) }
    }

}