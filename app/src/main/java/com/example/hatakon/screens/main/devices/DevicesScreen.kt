package com.example.hatakon.screens.main.devices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.hatakon.R
import com.example.hatakon.core.util.OnLifecycleEvent
import com.example.hatakon.screens.destinations.DeviceInfoScreenDestination
import com.example.hatakon.screens.main.devices.components.DeviceInfo
import com.example.hatakon.ui.components.TopAppBar
import com.example.hatakon.ui.theme.Background
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun DevicesScreen(
    navigator: DestinationsNavigator,
    viewModel: DeviceScreenViewModel = hiltViewModel()
) {

    val state = viewModel.uiState.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.loadDevices()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                label = stringResource(R.string.devices),
            )
        },
        containerColor = Background
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(top = it.calculateTopPadding()),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.devices) { device ->
                DeviceInfo(device = device, onClick = {
                    navigator.navigate(DeviceInfoScreenDestination(device))
                })
            }
        }
    }
}