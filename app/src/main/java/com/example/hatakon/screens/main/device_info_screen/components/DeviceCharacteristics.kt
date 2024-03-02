package com.example.hatakon.screens.main.device_info_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.Background

@Composable
fun DeviceCharacteristics(title: String, value: String) {
    Row {
        Box(
            modifier = Modifier.weight(1f).background(Background,shape= RoundedCornerShape(8.dp)).padding(vertical = 12.dp, horizontal = 16.dp),
            contentAlignment = CenterStart
        )
        {
            Text(
                text = title,
                style = AppTextStyle.RobotoRegular.sp14,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.width(6.dp))
        Box(
            modifier = Modifier.weight(1f).background(Background,shape= RoundedCornerShape(8.dp)).padding(vertical = 12.dp, horizontal = 16.dp),
            contentAlignment = CenterEnd
        )
        {
            Text(
                text = value,
                style = AppTextStyle.RobotoRegular.sp14,
                color = Color.White
            )
        }
    }
}