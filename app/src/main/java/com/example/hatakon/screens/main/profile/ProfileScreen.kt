package com.example.hatakon.screens.main.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.hatakon.screens.main.profile.components.UserInfoSection
import com.example.hatakon.core.util.OnLifecycleEvent
import com.example.hatakon.screens.destinations.DeviceInfoScreenDestination
import com.example.hatakon.screens.main.profile.components.DevicesChecksInfoSection
import com.example.hatakon.screens.main.profile.components.RecentDeviceSection
import com.example.hatakon.ui.components.TopAppBar
import com.example.hatakon.ui.theme.Background
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun ProfileScreen(
    navigator: DestinationsNavigator,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.loadRecentDevicesChecks()
        }
    }

    Scaffold(
        topBar = { TopAppBar(label = "Profile") },
        containerColor = Background
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
                .verticalScroll(state = rememberScrollState())
        ) {
            UserInfoSection(user = state.user)
            Spacer(modifier = Modifier.height(8.dp))
            if (state.recentDevicesChecks.isNotEmpty()) {
                DevicesChecksInfoSection(
                    recentDevicesChecks = state.recentDevicesChecks,
                    selectedSecureType =  state.selectedSecureType,
                    onSecureTypeClick = viewModel::onSecureTypeClick
                )
                Spacer(modifier = Modifier.height(10.dp))
                RecentDeviceSection(
                    devices = state.recentDevicesChecks,
                    onClick = {device->
                        navigator.navigate(DeviceInfoScreenDestination(device = device))
                    }
                )
            }
        }
    }
}