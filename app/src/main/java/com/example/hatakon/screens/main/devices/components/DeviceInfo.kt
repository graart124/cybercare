package com.example.hatakon.screens.main.devices.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hatakon.R
import com.example.hatakon.core.data.model.Device
import com.example.hatakon.core.data.model.DeviceType.Audio
import com.example.hatakon.core.data.model.DeviceType.Video
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.Background
import com.example.hatakon.ui.theme.NoInfoColor
import com.example.hatakon.ui.theme.NonSecureColor
import com.example.hatakon.ui.theme.PurpleDark
import com.example.hatakon.ui.theme.SecureColor

@Composable
fun DeviceInfo(device: Device, onClick: () -> Unit) {
    val secureColor = when (device.deviceSecurity) {
        true -> SecureColor
        false -> NonSecureColor
        null -> NoInfoColor
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = PurpleDark, shape = RoundedCornerShape(8.dp))
            .border(width = 2.dp, color = secureColor, shape = RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(45.dp)
                .background(Background, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = device.deviceBrand ?: "No brand",
                style = AppTextStyle.RobotoRegular.sp18,
                color = Color.White,
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(45.dp)
                .background(Background, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = device.deviceModel ?: "No model spec",
                style = AppTextStyle.RobotoRegular.sp18,
                color = Color.White,
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        DeviceTypeImage(device)
    }
}

@Composable
fun DeviceTypeImage(device: Device) {
    val iconId = when (device.getDeviceTypeEnum()) {
        Video -> R.drawable.ic_video
        Audio -> R.drawable.ic_audio
    }
    Box(
        modifier = Modifier
            .background(Background, shape = RoundedCornerShape(8.dp))
            .padding(10.dp), contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = iconId), contentDescription = "device type icon")
    }
}
