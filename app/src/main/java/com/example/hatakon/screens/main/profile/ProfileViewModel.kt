package com.example.hatakon.screens.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hatakon.core.data.model.SecureType
import com.example.hatakon.features.device.repository.DeviceRepository
import com.example.hatakon.features.user.repository.UserRepository
import com.example.hatakon.screens.main.profile.model.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()


    init {
        _uiState.update {
            it.copy(
                user = userRepository.getUserData()
            )
        }
        loadRecentDevicesChecks()
    }

    fun loadRecentDevicesChecks() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
            }
        ) {
            try {
                val recentDevices = deviceRepository.getAllRecentDevices()
                _uiState.update { it.copy(recentDevicesChecks = recentDevices) }
            } catch (e: Exception) {
                Log.d("MyLog",e.message?:"")
            }
        }
    }

    fun onSecureTypeClick(secureType: SecureType) {
        _uiState.update { it.copy(selectedSecureType = secureType) }
    }

}