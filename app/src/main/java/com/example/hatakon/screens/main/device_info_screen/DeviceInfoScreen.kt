package com.example.hatakon.screens.main.device_info_screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hatakon.R
import com.example.hatakon.core.data.model.Device
import com.example.hatakon.screens.main.device_info_screen.components.AnalyzeDataSection
import com.example.hatakon.screens.main.device_info_screen.components.DeviceCharacteristics
import com.example.hatakon.screens.main.device_info_screen.components.DeviceImage
import com.example.hatakon.screens.main.device_info_screen.components.DeviceSecureSection
import com.example.hatakon.ui.components.TopAppBar
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.Background
import com.example.hatakon.ui.theme.PinkSplash
import com.example.hatakon.ui.theme.PurpleDark
import com.example.hatakon.ui.theme.Roboto
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Composable
@Destination
fun DeviceInfoScreen(
    navigator: DestinationsNavigator,
    device: Device
) {
    val showAnalyzeData = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        showAnalyzeData.value = true
        delay(1500)
        showAnalyzeData.value = false
    }

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
        if (showAnalyzeData.value) {
            AnalyzeDataSection()
        } else {
            Column(
                modifier = Modifier
                    .verticalScroll(state = rememberScrollState())
                    .padding(top = it.calculateTopPadding())
                    .padding(horizontal = 12.dp, vertical = 14.dp)
            ) {
                DeviceSecureSection(deviceSecurity = device.deviceSecurity)
                Spacer(modifier = Modifier.height(16.dp))
                DeviceImage(imageLink = device.imageLink)
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.device_characteristics),
                    textAlign = TextAlign.Center,
                    style = AppTextStyle.RobotoSemiBold.sp28.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.height(6.dp))
                DeviceCharacteristicSection(device)
            }
        }
    }

}

@Composable
fun DeviceCharacteristicSection(device: Device) {

    val context = LocalContext.current

    val onLinkClick: (String) -> Unit = { link ->
        val uri: Uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }

    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = PinkSplash,
                fontSize =14.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            )
        ) {
            append("Click here")
            addStringAnnotation(
                tag = "URL",
                annotation = device.deviceInfoLink
                    ?: "https://www.google.com/${device.deviceType} ${device.deviceBrand}",
                start = 0,
                end = "Click here".length
            )
        }
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontSize =14.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            )
        ) {
            append(" to read more info about device")
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(PurpleDark, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 6.dp)
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        DeviceCharacteristics(title = "Brand", value = device.deviceBrand ?: "No info")
        Spacer(modifier = Modifier.height(6.dp))
        DeviceCharacteristics(title = "Model", value = device.deviceModel ?: "No info")
        Spacer(modifier = Modifier.height(6.dp))
        DeviceCharacteristics(title = "Type", value = device.deviceType ?: "No info")
        Spacer(modifier = Modifier.height(6.dp))
        DeviceCharacteristics(title = "Frequency", value = device.ghz ?: "No info")
        Spacer(modifier = Modifier.height(6.dp))
        DeviceCharacteristics(
            title = "Privacy shutter",
            value = if (device.privacyShutter == true) "Yes" else "No"
        )
        Spacer(modifier = Modifier.height(6.dp))
        DeviceCharacteristics(title = "Wi-Fi", if (device.wifi == true) "Yes" else "No")
        Spacer(modifier = Modifier.height(18.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ClickableText(
                text = annotatedString,
                onClick = { offset ->
                    annotatedString.getStringAnnotations("URL", offset, offset)
                        .firstOrNull()?.let { annotation ->
                            onLinkClick(annotation.item)
                        }
                }
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
    }
}
