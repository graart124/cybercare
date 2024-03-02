package com.example.hatakon.screens.main.device_info_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hatakon.core.data.model.Device
import com.example.hatakon.screens.main.device_info_screen.components.DeviceCharacteristics
import com.example.hatakon.ui.components.TopAppBar
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.Background
import com.example.hatakon.ui.theme.PurpleDark
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun DeviceInfoScreen(
    navigator: DestinationsNavigator,
    device: Device
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                label = "Device info",
                navigator = navigator,
                backButtonAvailable = true
            )
        },
        containerColor = Background
    ) {
        Column(
            modifier = Modifier
                .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 12.dp, vertical = 14.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .background(PurpleDark, shape = RoundedCornerShape(8.dp))
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    model = device.imageLink,
                    contentDescription = "device photo",
                    contentScale = ContentScale.FillWidth
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Device characteristics",
                textAlign = TextAlign.Center,
                style = AppTextStyle.RobotoSemiBold.sp28.copy(color= Color.White)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PurpleDark, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 6.dp)
            )
            {
                Spacer(modifier = Modifier.height(6.dp))
                DeviceCharacteristics(title = "Brand", value = device.deviceBrand?:"No info" )
                Spacer(modifier = Modifier.height(6.dp))
                DeviceCharacteristics(title = "Model", value = device.deviceModel?:"No info" )
                Spacer(modifier = Modifier.height(6.dp))
                DeviceCharacteristics(title = "Type", value = device.deviceType?:"No info" )
                Spacer(modifier = Modifier.height(6.dp))
                DeviceCharacteristics(title = "Frequency", value = device.ghz?:"No info" )
                Spacer(modifier = Modifier.height(6.dp))
                DeviceCharacteristics(title = "Privacy shutter", value = if(device.privacyShutter == true)  "Yes" else "No")
                Spacer(modifier = Modifier.height(6.dp))
                DeviceCharacteristics(title = "Wi-Fi", if(device.wifi == true)  "Yes" else "No")
            }

        }

    }

}