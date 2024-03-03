package com.example.hatakon.screens.main.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hatakon.R
import com.example.hatakon.core.data.model.Device
import com.example.hatakon.screens.main.devices.components.DeviceInfo
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.PurpleDark


@Composable
fun RecentDeviceSection(
    devices: List<Device>,
    onClick: (Device) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .background(color = PurpleDark, shape = RoundedCornerShape(8.dp))
            .padding(top = 16.dp, bottom = 6.dp)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.recent_checks),
            style = AppTextStyle.RobotoSemiBold.sp28.copy(Color.White)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            devices.take(10).map { device ->
                Column {
                    DeviceInfo(device = device, onClick = { onClick(device) })
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}