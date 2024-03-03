package com.example.hatakon.screens.main.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hatakon.core.data.model.Device
import com.example.hatakon.core.data.model.SecureType
import com.example.hatakon.core.data.model.getColor
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.PurpleDark

@Composable
fun DevicesChecksInfoSection(
    recentDevicesChecks: List<Device> = emptyList(),
    selectedSecureType: SecureType?,
    onSecureTypeClick: (SecureType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(color = PurpleDark, shape = RoundedCornerShape(8.dp))
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Check info",
            style = AppTextStyle.RobotoSemiBold.sp28.copy(Color.White)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DevicePieChart(recentDevicesChecks = recentDevicesChecks, selectedSecureType = selectedSecureType)
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                LessonTypeItem(secureType = SecureType.Secure, onClick = onSecureTypeClick)
                Spacer(modifier = Modifier.height(12.dp))
                LessonTypeItem(secureType = SecureType.NotSecure, onClick = onSecureTypeClick)
                Spacer(modifier = Modifier.height(12.dp))
                LessonTypeItem(secureType = SecureType.NoEnoughInfo, onClick = onSecureTypeClick)
            }
        }
    }
}

@Composable
fun LessonTypeItem(
    secureType: SecureType,
    onClick: (SecureType) -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onClick(secureType)
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color = secureType.getColor(), shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = secureType.displayName,
            style = AppTextStyle.RobotoSemiBold.sp18.copy(Color.White)
        )
    }
}


