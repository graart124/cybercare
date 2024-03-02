package com.example.hatakon.screens.main.select_device

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hatakon.R
import com.example.hatakon.ui.components.AddItemSection
import com.example.hatakon.ui.components.CheckClearButtons
import com.example.hatakon.ui.components.TopAppBar
import com.example.hatakon.ui.theme.Background
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun SelectDeviceScreen(
    navigator: DestinationsNavigator,
    viewModel: SelectDeviceViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value
    val actionResult = viewModel.actionResult.collectAsState().value

//    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }



    LaunchedEffect(key1 = actionResult) {
        if (!actionResult.info.isNullOrEmpty()) {
            viewModel.actionResult.value.info?.let { snackbarHostState.showSnackbar(it) }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                label = "Select device",
                navigator = navigator)
        },
        containerColor = Background
    ) {
        Column(
            modifier = Modifier
                .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 12.dp, vertical = 14.dp)
        ) {
            AddItemSection(
                title = "Select device type",
                value = state.selectedDeviceType,
                hint = "Choose device type",
                iconId = R.drawable.ic_arrow_down,
                onClick = {
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            AddItemSection(
                title = "Select device brand",
                value = state.selectedDeviceType,
                hint = "Choose device brand",
                iconId = R.drawable.ic_arrow_down,
                onClick = {
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            AddItemSection(
                title = "Select device model",
                value = state.selectedDeviceType,
                hint = "Choose device model",
                iconId = R.drawable.ic_arrow_down,
                onClick = {
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            CheckClearButtons(onDeleteClick = {

            }, onSaveClick = {

            })

        }
    }
}