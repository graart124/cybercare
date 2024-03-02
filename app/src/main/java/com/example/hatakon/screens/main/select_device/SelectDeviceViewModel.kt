package com.example.hatakon.screens.main.select_device

import androidx.lifecycle.ViewModel
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
        deviceRepository.getAllDeviceTypes(
            onSuccess = { deviceTypes ->
                _uiState.update { it.copy(deviceTypes = deviceTypes) }
            }, onFailure = {
                _actionResult.update { it.copy(info = it.toString(), success = false) }
            }
        )
    }
}