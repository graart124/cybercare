package com.example.hatakon.screens.main.device_info_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hatakon.R
import com.example.hatakon.ui.components.GifImage
import com.example.hatakon.ui.theme.AppTextStyle


@Composable
fun AnalyzeDataSection() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GifImage(
            gifId = R.drawable.analize_data
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.analyzing_data),
            style = AppTextStyle.RobotoRegular.sp24,
            color = Color.White
        )
    }
}